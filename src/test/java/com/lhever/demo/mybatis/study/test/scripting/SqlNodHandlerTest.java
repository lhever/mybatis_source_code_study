package com.lhever.demo.mybatis.study.test.scripting;

import com.lhever.demo.mybatis.study.common.Int2BinaryStringUtils;
import com.lhever.demo.mybatis.study.common.JsonUtils;
import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.XMLScriptBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/6/4 10:25
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/6/4 10:25
 * @modify by reason:{方法名}:{原因}
 */
public class SqlNodHandlerTest {

    public static final String node_xml = "com/lhever/demo/mybatis/study/test/scripting/TestBookMapper.xml";
    public static final String book_table_sql = "com/lhever/demo/mybatis/study/test/scripting/book_table.sql";

    private static SqlSessionFactory sqlSessionFactory;
    private static  Configuration configuration;

    private static DataSource dataSource;
    protected static ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
    protected static ObjectFactory objectFactory = new DefaultObjectFactory();
    protected static ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();


    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory, reflectorFactory);
    }


    @Before
    public void setup() throws Exception {
        dataSource = BaseDataTest.createBlogDataSource();
        BaseDataTest.runScript(dataSource, book_table_sql);
        BaseDataTest.runScript(dataSource, BaseDataTest.BLOG_DATA);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("Production", transactionFactory, dataSource);
        configuration = new Configuration(environment);
        configuration.setLazyLoadingEnabled(true);
        configuration.setUseActualParamName(false); // to test legacy style reference (#{0} #{1})
        configuration.addMapper(TestBookMapper.class);
        configuration.getTypeAliasRegistry().registerAlias(TestBook.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }




    @Test
    public void shouldSelectOne() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            SqlRunner exec = new SqlRunner(connection);
            Map<String, Object> row = exec.selectOne("SELECT * FROM book WHERE id = ?", 1);
            System.out.println(JsonUtils.object2Json(row, true));
            assertEquals(1, row.get("ID"));
        }
    }


    public Document getDocument() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setValidating(false);
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        builder.setEntityResolver(new XMLMapperEntityResolver());
        InputStream resourceAsStream = Resources.getResourceAsStream(node_xml);

        Document document = builder.parse(resourceAsStream);
        return document;
    }

    public SqlSource parseNode(Document document, String expression) {
        XPathParser xPathParser = new XPathParser(document, false);
        XNode xNode = xPathParser.evalNode(expression);
        System.out.println(xNode.getValueBasedIdentifier());

        XMLScriptBuilder xb = new XMLScriptBuilder(configuration, xNode);
        SqlSource sqlSource = xb.parseScriptNode();
        return sqlSource;
    }





    @Test
    public void testParseNode_findPosts() throws Exception {
        Document document = getDocument();
        parseNode(document, "/mapper/select[@id='findPosts']");
    }


    @Test
    public void testParseNode_addList() throws Exception {
        Document document = getDocument();
        SqlSource sqlSource = parseNode(document, "/mapper/insert[@id='addList']");

        DefaultSqlSession.StrictMap<Object> paramMap = new DefaultSqlSession.StrictMap<>();
        paramMap.put("list", getBookLst());

        MetaObject metaObject = newMetaObject(sqlSource);
        SqlNode rootSqlNode = (SqlNode) metaObject.getValue("rootSqlNode");
        Configuration configuration = (Configuration) metaObject.getValue("configuration");


        //BoundSql boundSql = sqlSource.getBoundSql(paramMap);
        BoundSql boundSql = getBoundSql(rootSqlNode, paramMap, configuration);

        System.out.println(boundSql.getSql());
        System.out.println(JsonUtils.object2Json(boundSql.getParameterMappings(), true));


    }


    //该方法内部逻辑，与sqlSource.getBoundSql等价
    public BoundSql getBoundSql(SqlNode rootSqlNode, Object parameterObject, Configuration configuration) {
        DynamicContext context = new DynamicContext(configuration, parameterObject);
        rootSqlNode.apply(context);

        System.out.println(context.getSql());

        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
        SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());

        //#{xxx} 替换为？ ，同时构建了ParamMapping列表
        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
        context.getBindings().forEach(boundSql::setAdditionalParameter);
        return boundSql;
    }

    public List<TestBook> getBookLst() {
        TestBook book1 = new TestBook("《java编程思想》", "第一版", 10d, new Date());
        TestBook book2 = new TestBook("scala handbook", "第一版", 10d, new Date());
        return Arrays.asList(book1, book2);
    }




    @Test
    public void testAddBook() throws Exception {
        List<TestBook> testBooks = getBookLst();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        TestBookMapper mapper = configuration.getMapper(TestBookMapper.class, sqlSession);
        mapper.addList(testBooks);
        sqlSession.commit();
        System.out.println(JsonUtils.object2Json(testBooks, true));

        System.out.println("*************************************************************");

        List<TestBook> all = mapper.getAll();
        System.out.println(JsonUtils.object2Json(all, true));
    }



    @Test
    public void test() {

        System.out.println(0x7fffffff);
        System.out.println(Int2BinaryStringUtils.toFullBinaryString(0x7fffffff, true));
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("aaaa", "aaaa");
    }










































}
