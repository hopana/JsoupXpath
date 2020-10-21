package org.seimicrawler.xpath.expr;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.seimicrawler.xpath.BaseTest;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2017/12/6.
 */
public class QingTest extends BaseTest {


    @Test
    public void imgTest1() throws Exception {
        ClassLoader loader = getClass().getClassLoader();
        URL url = loader.getResource("qing1.html");
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


    @Test
    public void imgTest3() throws Exception {
        String FILE_DOMAIN = "https://ssm.res.meizu.com/";

        ClassLoader loader = getClass().getClassLoader();
        URL url = loader.getResource("qing3.html");
        assert url != null;
        File contentFile = new File(url.toURI());
        String context = FileUtils.readFileToString(contentFile, StandardCharsets.UTF_8);
        Elements root = Jsoup.parse(context).children();

        Elements imgs = root.select("img");
        for (Element img : imgs) {
            String tempSrc = img.attr("src");
            String finalSrc = FILE_DOMAIN + tempSrc.replace("temp", "content");
            img.attr("src", finalSrc);
        }

        System.out.println(root.toString());
    }


    @Test
    public void imgTest4() throws Exception {
        String FILE_DOMAIN = "https://ssm.res.meizu.com/";
        String content = "<p>gghhhh</p><img src=\"temp/2020/10/21/76467/1603249482288.jpg\"/><p>hhhh</p>";


        Elements root = Jsoup.parse(content).children();
        Elements elements = root.select("img");

        if (elements.size() > 0) {
            for (Element img : elements) {
                String tempSrc = img.attr("src");
                String finalSrc = FILE_DOMAIN + tempSrc.replace("temp", "content");
                img.attr("src", finalSrc);
            }
        }

        System.out.println(root.select("body").html());
    }

}