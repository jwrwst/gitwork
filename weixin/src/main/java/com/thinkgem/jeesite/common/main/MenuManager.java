package com.thinkgem.jeesite.common.main;

import com.thinkgem.jeesite.common.pojo.Button;
import com.thinkgem.jeesite.common.pojo.CommonButton;
import com.thinkgem.jeesite.common.pojo.ComplexButton;
import com.thinkgem.jeesite.common.pojo.Menu;
import com.thinkgem.jeesite.common.pojo.ViewButton;
import com.thinkgem.jeesite.common.util.PropertiesUtil;
import com.thinkgem.jeesite.modules.weixin.util.ConfigUtils;
import com.thinkgem.jeesite.modules.weixin.util.WeixinUtil;

/**
 * 菜单管理器类
 * 
 * @author WHW
 * @date 2014年12月3日
 */
public class MenuManager {
    private static String baseUrl = new PropertiesUtil("jeesite.properties")
            .readValue("serverUrl");

    public static void main(String[] args) {
        // 调用接口获取access_token
        String at = WeixinUtil.getAccessToken(ConfigUtils.APPID,ConfigUtils.ACCESS_TOKEN_URL);
        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), at);
            // 判断菜单创建结果
            if (0 == result)
                System.out.println("菜单创建成功");
            else
                System.out.println("菜单创建失败，错误码：" + result);
        }
    }

    /**
     * 组装菜单数据
     * 
     * @return
     */
    private static Menu getMenu() {
    	CommonButton btn11 = new CommonButton();
		btn11.setName("测试按钮1");
		btn11.setType("click");
		btn11.setKey("1");

		CommonButton btn12 = new CommonButton();
        btn12.setName("测试按钮2");
        btn12.setType("click");
        btn12.setKey("2");

        CommonButton btn13 = new CommonButton();
        btn13.setName("测试按钮3");
        btn13.setType("click");
        btn13.setKey("3");

        CommonButton btn21 = new CommonButton();
        btn21.setName("测试21");
        btn21.setType("click");
        btn21.setKey("21");
        
        CommonButton btn22 = new CommonButton();
        btn22.setName("测试22");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("测试23");
        btn23.setType("click");
        btn23.setKey("23");

        ViewButton btn31 = new ViewButton();
        btn31.setName("前往百度");
        btn31.setType("view");
        btn31.setUrl("http://wap.baidu.com/");

        CommonButton btn32 = new CommonButton();
        btn32.setName("点我呀");
        btn32.setType("click");
        btn32.setKey("32");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("测试按钮1");
        mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("测试按钮2");
        mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23 });

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("测试按钮3");
        mainBtn3.setSub_button(new Button[] { btn31, btn32 });

        /**
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
         * 
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1,mainBtn2,mainBtn3 });

        return menu;
    }
    
}
