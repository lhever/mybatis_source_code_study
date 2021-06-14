package com.lhever.demo.mybatis.study.test.ParamNameResolver;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.reflection.ParamNameUtil;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/11 20:27
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/11 20:27
 * @modify by reason:{方法名}:{原因}
 */
public class ParamNameResolverTestObj {

    public void aMethod(@Param("aVal") int a, @Param("bVal") int b) {

    }

    public void bMethod(int a, int b) {

    }

    public void cMethod(int intVal, RowBounds rb, ResultHandler handler, @Param("str") String string, Boolean bool) {

    }


    public static class ParamNameResolver {

        private static final String GENERIC_NAME_PREFIX = "param";

        /**
         * <p>
         * The key is the index and the value is the name of the parameter.<br />
         * The name is obtained from {@link org.apache.ibatis.annotations.Param} if specified. When {@link org.apache.ibatis.annotations.Param} is not specified,
         * the parameter index is used. Note that this index could be different from the actual index
         * when the method has special parameters (i.e. {@link RowBounds} or {@link org.apache.ibatis.session.ResultHandler}).
         * </p>
         * <ul>
         * <li>aMethod(@Param("M") int a, @Param("N") int b) -&gt; {{0, "M"}, {1, "N"}}</li>
         * <li>aMethod(int a, int b) -&gt; {{0, "0"}, {1, "1"}}</li>
         * <li>aMethod(int a, RowBounds rb, int b) -&gt; {{0, "0"}, {2, "1"}}</li>
         * </ul>
         */
        private final SortedMap<Integer, String> names;

        private boolean hasParamAnnotation;

        ///////改造过源码，避免Configuration对象null指针
        public ParamNameResolver(boolean isUseActualParamName, Method method) {
            final Class<?>[] paramTypes = method.getParameterTypes();
            final Annotation[][] paramAnnotations = method.getParameterAnnotations();
            final SortedMap<Integer, String> map = new TreeMap<>();
            int paramCount = paramAnnotations.length;
            // get names from @Param annotations
            for (int paramIndex = 0; paramIndex < paramCount; paramIndex++) {
                if (isSpecialParameter(paramTypes[paramIndex])) {
                    // skip special parameters
                    continue;
                }
                String name = null;
                for (Annotation annotation : paramAnnotations[paramIndex]) {
                    if (annotation instanceof org.apache.ibatis.annotations.Param) {
                        hasParamAnnotation = true;
                        name = ((org.apache.ibatis.annotations.Param) annotation).value();
                        break;
                    }
                }
                if (name == null) {
                    // @Param was not specified.
                    if (isUseActualParamName) {
                        name = getActualParamName(method, paramIndex);
                    }
                    if (name == null) {
                        // use the parameter index as the name ("0", "1", ...)
                        // gcode issue #71
                        name = String.valueOf(map.size());
                    }
                }
                map.put(paramIndex, name);
            }
            names = Collections.unmodifiableSortedMap(map);
        }

        private String getActualParamName(Method method, int paramIndex) {
            return ParamNameUtil.getParamNames(method).get(paramIndex);
        }

        private static boolean isSpecialParameter(Class<?> clazz) {
            return RowBounds.class.isAssignableFrom(clazz) || org.apache.ibatis.session.ResultHandler.class.isAssignableFrom(clazz);
        }

        /**
         * Returns parameter names referenced by SQL providers.
         */
        public String[] getNamesArray() {
            return names.values().toArray(new String[0]);
        }

        public Map<Integer, String> getNames() {
            return names;

        }



        /**
         * <p>
         * A single non-special parameter is returned without a name.
         * Multiple parameters are named using the naming rule.
         * In addition to the default names, this method also adds the generic names (param1, param2,
         * ...).
         * </p>
         */
        public Object getNamedParams(Object[] args) {
            final int paramCount = names.size();
            if (args == null || paramCount == 0) {
                return null;
            } else if (!hasParamAnnotation && paramCount == 1) {
                return args[names.firstKey()];
            } else {
                final Map<String, Object> param = new MapperMethod.ParamMap<>();
                int i = 0;
                for (Map.Entry<Integer, String> entry : names.entrySet()) {
                    param.put(entry.getValue(), args[entry.getKey()]);
                    // add generic param names (param1, param2, ...)
                    final String genericParamName = GENERIC_NAME_PREFIX + String.valueOf(i + 1);
                    // ensure not to overwrite parameter named with @Param
                    if (!names.containsValue(genericParamName)) {
                        param.put(genericParamName, args[entry.getKey()]);
                    }
                    i++;
                }
                return param;
            }
        }
    }





}
