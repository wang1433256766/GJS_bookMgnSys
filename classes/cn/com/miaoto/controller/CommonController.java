package cn.com.miaoto.controller;

import cn.com.miaoto.common.ReturnMsg;
import cn.com.miaoto.common.SynUserReturn;
import cn.com.miaoto.common.SystemSetting;
import cn.com.miaoto.common.UserInfoSynchronize;
import cn.com.miaoto.common.mvcBean.BaseController;
import cn.com.miaoto.modules.book.inf.GetBookByBatchService;
import cn.com.miaoto.modules.book.inf.UpdateBookService;
import cn.com.miaoto.modules.book.model.GetBookByBatchReq;
import cn.com.miaoto.modules.book.model.GetBookByBatchResp;
import cn.com.miaoto.modules.book.model.UpdateBookReq;
import cn.com.miaoto.modules.book.model.UpdateBookResp;
import cn.com.miaoto.modules.common.inf.SendNotiService;
import cn.com.miaoto.modules.common.model.SendNotiReq;
import cn.com.miaoto.modules.common.model.SendNotiResp;
import cn.com.miaoto.modules.setting.inf.AddSettingService;
import cn.com.miaoto.modules.setting.inf.GetSettingService;
import cn.com.miaoto.modules.setting.inf.UpdateSettingService;
import cn.com.miaoto.modules.setting.model.*;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;
import cn.com.miaoto.pojo.Setting;
import cn.com.miaoto.util.StringUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * common controller
 * Created by hx on 2017/7/31.
 */
@Controller
@RequestMapping("/")
public class CommonController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @Resource
    private SystemSetting systemSetting;

    @Resource
    private GetBookByBatchService getBookByBatchService;

    @Resource
    private AddSettingService addSettingService;

    @Resource
    private UpdateSettingService updateSettingService;

    @Resource
    private SendNotiService sendNotiService;

    @Resource
    private GetSettingService getSettingService;

    @Resource
    private UpdateBookService updateBookService;

    @Resource
    private UserInfoSynchronize userInfoSynchronize;

    @Resource
    private SynUserReturn synUserReturn;

    /**
     * 添加设置
     *
     * @param setting 设置内容
     * @return ReturnMsg
     */
    @RequestMapping(value = "addSetting", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg addSetting(Setting setting) {
        ReturnMsg msg = new ReturnMsg();
        try {
            AddSettingReq reqBean = new AddSettingReq(setting);
            AddSettingResp respBean = new AddSettingResp();
            respBean = addSettingService.addSetting(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("addSetting failed");
                msg.setMsg(1, "addSetting failed", null);
                return msg;
            }
            msg.setMsg(0, "addSetting success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("FeedbackController addSetting() catch exception : ", e);
            msg.setMsg(1, "addSetting failed", null);
            return msg;
        }
    }

    /**
     * 下载excel文件(批次书目)
     *
     * @param batchId  批次id
     * @param response servlet response
     * @return ReturnMsg
     */
    @RequestMapping(value = "downloadXlsx", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg downloadXlsx(int batchId, HttpServletResponse response) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetBookByBatchReq reqBean = new GetBookByBatchReq(batchId);
            GetBookByBatchResp respBean = new GetBookByBatchResp();
            respBean = getBookByBatchService.getBookByBatch(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get book by batch failed");
                msg.setMsg(1, "get book by batch failed", null);
                return msg;
            }
            if (respBean.getBookEntityList() == null) {
                LOGGER.error("get book by batch failed");
                msg.setMsg(1, "get book by batch is null", null);
                return msg;
            }
            if (respBean.getBookEntityList().size() == 0) {
                LOGGER.error("get book by batch size is 0");
                msg.setMsg(1, "get book by batch size is 0", null);
                return msg;
            }

            XSSFWorkbook workbook;
            workbook = new XSSFWorkbook();
            //获取参数个数作为excel列数
            int columeCount = 7;
            XSSFSheet sheet = workbook.createSheet("批次书目汇总");
            //创建第一栏
            XSSFRow headRow = sheet.createRow(0);
            String[] titleArray = {"序列", "书名", "出版社", "作者", "索书号", "条形码", "价格"};
            int[] widths = {1000, 8000, 6000, 6000, 4000, 4000, 4000};
            for (int m = 0; m < columeCount; m++) {
                XSSFCell cell = headRow.createCell(m);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                sheet.setColumnWidth(m, widths[m]);
                XSSFCellStyle style = workbook.createCellStyle();
                XSSFFont font = workbook.createFont();
                font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                short color = HSSFColor.BLACK.index;
                font.setColor(color);
                style.setFont(font);
                //填写数据
                cell.setCellStyle(style);
                cell.setCellValue(titleArray[m]);

            }
            int index = 0;
            //写入数据
            for (BookEntity entity : respBean.getBookEntityList()) {
                XSSFRow row = sheet.createRow(index + 1);
                for (int n = 0; n < columeCount; n++)
                    row.createCell(n);
                row.getCell(0).setCellValue(index + 1);
                row.getCell(1).setCellValue(entity.getBook().getName());
                row.getCell(2).setCellValue(entity.getBook().getPublisher());
                row.getCell(3).setCellValue(entity.getBook().getAuthor());
                row.getCell(4).setCellValue(entity.getBook().getClaimNumber());
                row.getCell(5).setCellValue(StringUtil.fillZero(entity.getBarcode()));
                row.getCell(6).setCellValue(entity.getBatchPrice());
                index++;
            }

            //写到磁盘上
            try {
                String filename = respBean.getBatchInfo().getMark() + ".xlsx";
//                String mimeType = URLConnection.guessContentTypeFromName(filename);
//                if (mimeType == null) {
//                    LOGGER.debug("mimetype is not detectable, will take default");
//                    mimeType = "application/octet-stream";
//                }
                String mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", "inline; filename=\"" + new String(filename.getBytes(), "iso-8859-1") + "\"");

                OutputStream os = response.getOutputStream();
                workbook.write(os);

                os.flush();
                os.close();
                msg.setMsg(0, "success", null);
                return msg;
            } catch (Exception e) {
                msg.setStatus(1);
                msg.setMsg("failed");
                return msg;
            }
        } catch (Exception e) {
            LOGGER.error("FeedbackController downloadXlsx() catch exception : ", e);
            msg.setMsg(1, "downloadXlsx failed", null);
            return msg;
        }
    }

    /**
     * 更新设置
     *
     * @param maxRenewal            最大续约次数
     * @param borrowTime            借阅时间
     * @param maxBorrowed           最大借阅数量
     * @param renewPeriod           续约时间
     * @param foreignStart          外文条形码起始号
     * @param reminderTitle         归还提醒站内信标题
     * @param reminderTemplate      归还提醒站内信正文模板
     * @param reminderMsgTitle      归还提醒邮件标题
     * @param reminderMsgTemplate   归还提醒邮件正文模板
     * @param followTitle           关注提醒站内信标题
     * @param followTemplate        关注提醒站内信正文模板
     * @param followMsgTitle
     * @param followMsgTemplate
     * @param smtpHost
     * @param smtpSocketFactoryPort
     * @param smtpPort
     * @param email
     * @param emailPassword
     * @param feedbackTitle
     * @return
     */
    @RequestMapping(value = "updateSetting", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg updateSetting(
            String maxRenewal,
            String borrowTime,
            String maxBorrowed,
            String renewPeriod,
            String foreignStart,
            String reminderTitle,
            String reminderTemplate,
            String reminderMsgTitle,
            String reminderMsgTemplate,
            String followTitle,
            String followTemplate,
            String followMsgTitle,
            String followMsgTemplate,
            String smtpHost,
            String smtpSocketFactoryPort,
            String smtpPort,
            String email,
            String emailPassword,
            String feedbackTitle) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("maxRenewal", maxRenewal);
            map.put("borrowTime", borrowTime);
            map.put("maxBorrowed", maxBorrowed);
            map.put("renewPeriod", renewPeriod);
            map.put("foreignStart", foreignStart);
            map.put("foreignStart", foreignStart);
            map.put("smtpSocketFactoryPort", smtpSocketFactoryPort);
            map.put("smtpPort", smtpPort);
            map.put("email", email);
            map.put("smtpHost", smtpHost);
            map.put("emailPassword", emailPassword);
            map.put("feedbackTitle", feedbackTitle);
            map.put("reminderTitle", reminderTitle);
            map.put("reminderTemplate", reminderTemplate);
            map.put("reminderMsgTitle", reminderMsgTitle);
            map.put("reminderMsgTemplate", reminderMsgTemplate);
            map.put("followTitle", followTitle);
            map.put("followTemplate", followTemplate);
            map.put("followMsgTitle", followMsgTitle);
            map.put("followMsgTemplate", followMsgTemplate);
            UpdateSettingReq reqBean = new UpdateSettingReq(map);
            UpdateSettingResp respBean = new UpdateSettingResp();
            respBean = updateSettingService.updateSetting(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("update setting failed");
                msg.setMsg(1, "update setting failed", null);
                return msg;
            }
            systemSetting.init();
        } catch (Exception e) {
            LOGGER.error("CommonController updateSetting() catch Exception : ", e);
            msg.setMsg(1, "update setting failed", null);
            return msg;
        }
        msg.setMsg(0, "update setting success", null);
        return msg;
    }

    /**
     * 发送通知
     *
     * @param title   通知标题
     * @param context 通知内容
     * @return ReturnMsg
     */
    @RequestMapping(value = "sendNoti", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg sendNoti(String title, String context) {
        ReturnMsg msg = new ReturnMsg();
        try {
            SendNotiReq reqBean = new SendNotiReq(title, context);
            SendNotiResp respBean = new SendNotiResp();
            respBean = sendNotiService.sendNotiRecord(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("send noti failed");
                msg.setMsg(1, "send noti failed", null);
                return msg;
            }
            msg.setMsg(0, "send noti success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("CommonController sendNoti() catch exception : ", e);
            msg.setMsg(1, "sendNoti failed", null);
            return msg;
        }
    }

    /**
     * 获取设置
     *
     * @return ReturnMsg
     */
    @RequestMapping(value = "getSetting", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg getSetting() {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetSettingReq reqBean = new GetSettingReq();
            GetSettingResp respBean = new GetSettingResp();
            respBean = getSettingService.getSetting(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("getSetting failed");
                msg.setMsg(1, "getSetting failed", null);
                return msg;
            }
            msg.setMsg(0, "getSetting success", respBean.getSettingList());
            return msg;
        } catch (Exception e) {
            LOGGER.error("CommonController getSetting() catch exception : ", e);
            msg.setMsg(1, "getSetting failed", null);
            return msg;
        }
    }

    /**
     * 上传桌面
     *
     * @param file 二进制文件
     * @param bid  书目id
     * @return ReturnMsg
     */
    @RequestMapping(value = "uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg uploadImg(MultipartFile file, Integer bid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            //String fileName = file.getOriginalFilename();
            String oriName = file.getOriginalFilename();
            String suffix = oriName.substring(oriName.lastIndexOf(".") + 1);

            SimpleDateFormat format = new SimpleDateFormat("-yyyyMMdd-HHmmssSSS");
            String time = format.format(Calendar.getInstance().getTime());
            //String random = StringUtil.createRandomCharData(4);
            String dir = StringUtil.getXmlPath() + "cover/";

            String fileName = bid + time + "." + suffix;
            File targetFile = new File(dir, fileName);
            LOGGER.debug("upload cover : dir = {}, filename = {}", dir, fileName);
            if (!targetFile.exists()) {
                boolean isSuccess = targetFile.mkdirs();
                if (isSuccess) {
                    LOGGER.error("'mkdir failed, dir={}", dir);
                }
            }

            //保存
            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                LOGGER.error("upload cover failed", e);
                msg.setMsg(1, "failed", null);
                return msg;
            }

            // 更新book.cover
            Book updateBook = new Book();
            updateBook.setCover(fileName);
            updateBook.setBid(bid);

            UpdateBookResp respBean = new UpdateBookResp();
            UpdateBookReq reqBean = new UpdateBookReq(updateBook);
            respBean = updateBookService.updateBook(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("CommonController uploadImg() failed");
                msg.setMsg(1, "failed", fileName);
                return msg;
            }

            msg.setMsg(0, "success", fileName);
            return msg;
        } catch (Exception e) {
            LOGGER.error("CommonController uploadImg() catch exception : ", e);
            msg.setMsg(1, "failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "synUser", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg synUser() {
        ReturnMsg msg = new ReturnMsg();
        try {
            userInfoSynchronize.excute();
            msg.setMsg(0, "syn user start", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("CommonController synUser() catch exception : ", e);
            msg.setMsg(1, "failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "getSynRes", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getSynRes() {
        ReturnMsg msg = new ReturnMsg();
        try {
            synUserReturn.setAddUserList(StringUtil.clearSensitive(synUserReturn.getAddUserList()));
            synUserReturn.setDeleteUserList(StringUtil.clearSensitive(synUserReturn.getDeleteUserList()));
            synUserReturn.setUpdateUserList(StringUtil.clearSensitive(synUserReturn.getUpdateUserList()));
            msg.setMsg(0, "success", synUserReturn);
            return msg;
        } catch (Exception e) {
            LOGGER.error("CommonController getSynRes() catch exception : ", e);
            msg.setMsg(1, "failed", null);
            return msg;
        }
    }
}
