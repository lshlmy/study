package com.lshlmy.study.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.lshlmy.study.common.util.VerifycodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @description 首页
 * @author lshlmy
 */
@Controller
public class IndexController {

	/**
	 * 生成验证码
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "captcha", method = RequestMethod.GET)
	public void captcha(HttpServletResponse response, Model model) throws IOException {
		String textCode = VerifycodeUtils.generateTextCode(4);
		BufferedImage bufferedImage = VerifycodeUtils.generateImageCode(textCode, 60, 22, 0, true, new Color(255, 255, 255), null, null);
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		model.addAttribute("loginVerifycode", textCode);
		ImageIO.write(bufferedImage, "png", response.getOutputStream());
	}
}
