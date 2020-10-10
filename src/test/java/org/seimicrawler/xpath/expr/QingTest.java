package org.seimicrawler.xpath.expr;

import com.sun.tools.hat.internal.model.Root;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.seimicrawler.xpath.BaseTest;
import org.seimicrawler.xpath.antlr.XpathLexer;
import org.seimicrawler.xpath.antlr.XpathParser;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.core.XpathProcessor;
import org.seimicrawler.xpath.exception.DoFailOnErrorHandler;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2017/12/6.
 */
public class QingTest extends BaseTest {


    @Test
    public void imgTest1() throws Exception {
        ClassLoader loader = getClass().getClassLoader();
        URL url = loader.getResource("qing.html");
        assert url != null;
        File contentFile = new File(url.toURI());
        String context = FileUtils.readFileToString(contentFile, StandardCharsets.UTF_8);
        Elements root = Jsoup.parse(context).children();

        Elements img = root.select("img[src='https://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg']");
        img.attr("data-pass", "1");

        System.out.println(root.toString());
    }


    @Test
    public void imgTest2() throws Exception {
        ClassLoader loader = getClass().getClassLoader();
        URL url = loader.getResource("qing2.html");
        assert url != null;
        File contentFile = new File(url.toURI());
        String context = FileUtils.readFileToString(contentFile, StandardCharsets.UTF_8);
        Elements root = Jsoup.parse(context).children();

        Elements img = root.select("img[src='https://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg'][data-pass=0]");
        img.attr("test", "hello world");

        System.out.println(root.toString());
    }

}