package com.xiaopang.core.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaopang.core.utils.UtilBean;
import com.xiaopang.core.utils.UtilCollection;
import com.xiaopang.core.utils.UtilObject;
import com.xiaopang.core.utils.UtilString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author necho.duan
 * @title: PageUtil
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 11:42
 */
public class PageUtil {
    private static final Pattern SQL_REGEX = Pattern.compile("[ '\",;`()-+#]");

    public PageUtil() {
    }

    public static <T> Page<T> toPage(PageParam pageParam) {
        Page<T> page = new Page(pageParam.getCurrentPage(), pageParam.getPageSize());
        if (pageParam instanceof PageReqParam) {
            PageReqParam pageReqParam = (PageReqParam)pageParam;
            page.setAscs(pageReqParam.getAscs());
            page.setDescs(pageReqParam.getDescs());
        }

        return page;
    }

    public static <T> Page<T> toPage(IPage<?> page, Class<T> target) {
        Page<T> pageResult = new Page();
        pageResult.setCurrent(page.getCurrent());
        pageResult.setSize(page.getSize());
        pageResult.setPages(page.getPages());
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(UtilBean.copy(page.getRecords(), target));
        return pageResult;
    }

    public static <T> Page<T> toPage(IPage<?> page, List<T> records) {
        Page<T> pageResult = new Page();
        pageResult.setCurrent(page.getCurrent());
        pageResult.setSize(page.getSize());
        pageResult.setPages(page.getPages());
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(records);
        return pageResult;
    }

    public static <T, R> Page<R> toPage(IPage<T> page, Function<T, R> function) {
        Page<R> pageResult = new Page();
        pageResult.setCurrent(page.getCurrent());
        pageResult.setSize(page.getSize());
        pageResult.setPages(page.getPages());
        pageResult.setTotal(page.getTotal());
        List<R> data = new ArrayList();
        Iterator<T> var4 = page.getRecords().iterator();

        while(var4.hasNext()) {
            T record = var4.next();
            data.add(function.apply(record));
        }

        pageResult.setRecords(data);
        return pageResult;
    }

    public static <T> PageResult<T> toPageResult(IPage<T> page) {
        PageResult<T> respParam = new PageResult();
        respParam.setCurrentPage(page.getCurrent());
        respParam.setPageSize(page.getSize());
        respParam.setTotalPage(page.getPages());
        respParam.setTotalNum(page.getTotal());
        respParam.setData(page.getRecords());
        return respParam;
    }

    public static <T> PageResult<T> toPageResult(IPage<?> page, Class<T> target) {
        PageResult<T> pageResult = new PageResult();
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalPage(page.getPages());
        pageResult.setTotalNum(page.getTotal());
        pageResult.setData(UtilBean.copy(page.getRecords(), target));
        return pageResult;
    }

    public static <T, R> PageResult<R> toPageResult(IPage<T> page, Function<T, R> function) {
        PageResult<R> pageResult = new PageResult();
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalPage(page.getPages());
        pageResult.setTotalNum(page.getTotal());
        if (!UtilCollection.isEmpty(page.getRecords())) {
            pageResult.setData((List)page.getRecords().stream().map(function).collect(Collectors.toList()));
        }

        return pageResult;
    }

    public static List<String> filter(List<String> paramList) {
        return UtilObject.isEmpty(paramList) ? paramList : (List)paramList.stream().map(PageUtil::filter).collect(Collectors.toList());
    }

    public static String filter(String param) {
        return UtilString.isBlank(param) ? param : SQL_REGEX.matcher(param).replaceAll("");
    }
}
