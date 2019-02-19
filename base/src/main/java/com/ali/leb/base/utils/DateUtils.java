package com.ali.leb.base.utils;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * 日期处理
 * 
 */
public class DateUtils {

	private static Logger logger = Logger.getLogger(DateUtils.class);
	/**
	 * 公安部要求的日期格式yyyyMMddHHmmss
	 */
	public static final String DATESTR_FORMATTER_GA = "yyyyMMddHHmmss";
	/**
	 * 公安部要求的日期格式yyyy-MM-dd HH:mm:ss
	 */
	public static final String TIME_LINE = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 年月日格式日期yyyyMMdd
	 */
	public static final String DATE = "yyyyMMdd";

	/**
	 * 年月格式日期yyyyMM
	 */
	public static final String DATE_YEAR_MONTH = "yyyyMM";

	/**
	 * 带中划线的日期格式yyyy-MM
	 */
	public static final String YEAR_MONTH_LINE = "yyyy-MM";

	/**
	 * 带中划线的日期格式yyyy-MM-dd
	 */
	public static final String DATA_LINE = "yyyy-MM-dd";

	/**
	 * 日期格式YYYY年MM月
	 */
	public static final String CHINESE_YEAR_MONTH = "yyyy年MM月";

	/**
	 * %s年%s月字符串
	 */
	public static final String YEAR_MONTH_STR = "%s年%s月";

	/**
	 * 图表中的日期格式，格式为“yyyy年MM月dd日”
	 * 
	 * @return
	 */
	public static DateFormat getDateFormat() {
		return DateFormat.getDateInstance(1, Locale.CHINA);
	}

	/**
	 * 将日期格式化为"yyyy-MM"字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatYearMonth(Date date) {
		return doFormatDate(date, "yyyy-MM");
	}

	/**
	 * 返回日期所在的季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeasons(Date date) {
		int m = getMonth(date);
		if (m <= 0) {
			return 0;
		} else if (m < 4)
			return 1;
		else if (m < 7)
			return 2;
		else if (m < 10)
			return 3;
		else if (m < 13)
			return 4;
		else
			return 0;
	}

	/**
	 * 给定日期所在的季度，并返回该季度的第一天日期,如果指定日期错误，返回null
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNowSeasonsFirstDay(Date date) {
		int m = getSeasons(date);
		if (m > 0) {
			if (m == 1) {
				return stringToDate(getYear(date) + "-01-01");
			} else if (m == 2) {
				return stringToDate(getYear(date) + "-04-01");
			} else if (m == 3) {
				return stringToDate(getYear(date) + "-07-01");
			} else {
				return stringToDate(getYear(date) + "-10-01");
			}
		}
		return null;
	}

	/**
	 * 得到某年的最后一天的日期
	 * 
	 * @param year
	 * @return
	 */
	public static Date getYearLastDay(String year) {
		if (year == null || "".equals(year))
			return null;
		Date nd = stringToDate(year + "-01-01");
		return addDay(addYear(nd, 1), -1);
	}

	/**
	 * 得到下个月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getNextMonthFirstDay(String year, String month) {
		if (year == null || "".equals(year) || month == null
				|| "".equals(month))
			return null;
		Date nd = stringToDate(year + "-" + month + "-01");
		return addMonth(nd, 1);
	}

	/**
	 * 得到某年月的最后一天的日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthLastDay(String year, String month) {
		if (year == null || "".equals(year) || month == null
				|| "".equals(month))
			return null;
		Date nd = stringToDate(year + "-" + month + "-01");
		return addDay(addMonth(nd, 1), -1);
	}

	/**
	 * 给一个时间加上N秒钟或减去N秒钟后得到一个新的日期
	 * 
	 * @param startDate
	 *            需要增加的日期时间
	 * @param addnos
	 *            添加的秒钟数，可以是正数也可以是负数
	 * @return 操作后的日期
	 */
	public static Date addSecond(Date startDate, int addnos) {
		if (startDate == null)
			return null;
		Calendar cc = Calendar.getInstance();
		cc.setTime(startDate);
		cc.add(Calendar.SECOND, addnos);
		return cc.getTime();
	}

	/**
	 * 给一个时间加上N分种或减去N分种后得到一个新的日期
	 * 
	 * @param startDate
	 *            需要增加的日期时间
	 * @param addnos
	 *            添加的分钟数，可以是正数也可以是负数
	 * @return 操作后的日期
	 */
	public static Date addMinute(Date startDate, int addnos) {
		if (startDate == null) {
			return null;
		}
		Calendar cc = Calendar.getInstance();
		cc.setTime(startDate);
		cc.add(Calendar.MINUTE, addnos);
		return cc.getTime();
	}

	/**
	 * 给一个时间加上一定的小时得到一个新的日期
	 * 
	 * @param startDate
	 *            需要增加的日期时间
	 * @param addnos
	 *            添加的小时数，可以是正数也可以是负数
	 * @return 操作后的日期
	 */
	public static Date addHour(Date startDate, int addnos) {
		if (startDate == null) {
			return null;
		}
		Calendar cc = Calendar.getInstance();
		cc.setTime(startDate);
		cc.add(Calendar.HOUR, addnos);
		return cc.getTime();
	}

	/**
	 * 给一个日期加上N天或减去N天得到一个新的日期
	 * 
	 * @param startDate
	 *            需要增加的日期时间
	 * @param addnos
	 *            添加的天数，可以是正数也可以是负数
	 * @return 操作后的日期
	 */
	public static Date addDay(Date startDate, int addnos) {
		if (startDate == null) {
			return null;
		}
		Calendar cc = Calendar.getInstance();
		cc.setTime(startDate);
		cc.add(Calendar.DATE, addnos);
		return cc.getTime();
	}

	/**
	 * ｛年月日，加天数｝
	 * 
	 * @param startDate
	 *            20170804
	 * @param add
	 *            1|-1
	 * @return String 20170805
	 */
	public static String addDay(String startDate, int add) {
		if (StringUtils.isBlank(startDate)) {
			return null;
		}
		Date date = strToDate(startDate, "yyyymmdd");
		Calendar cc = Calendar.getInstance();
		cc.setTime(date);
		cc.add(Calendar.DATE, add);

		return doFormatDate(cc.getTime(), "yyyymmdd");
	}

	/**
	 * 给一个日期加上N月后或减去N月后得到的一个新日期
	 * 
	 * @param startDate
	 *            需要增加的日期时间
	 * @param addnos
	 *            添加的月数，可以是正数也可以是负数
	 * @return 操作后的日期
	 */
	public static Date addMonth(Date startDate, int addnos) {
		if (startDate == null) {
			return null;
		}
		Calendar cc = Calendar.getInstance();
		cc.setTime(startDate);
		cc.add(Calendar.MONTH, addnos);
		return cc.getTime();

	}

	/**
	 * 给一个日期加上N年后或减去N年后得到的一个新日期
	 * 
	 * @param startDate
	 *            需要增加的日期时间
	 * @param addnos
	 *            添加的年数，可以是正数也可以是负数
	 * @return 操作后的日期
	 */
	public static Date addYear(Date startDate, int addnos) {
		if (startDate == null) {
			return null;
		}
		Calendar cc = Calendar.getInstance();
		cc.setTime(startDate);
		cc.add(Calendar.YEAR, addnos);
		return cc.getTime();
	}

	/**
	 * 计算两个日期相差的月数
	 * 
	 * @param st
	 *            起始日期
	 * @param end
	 *            结束日期
	 * @return
	 */
	public static int compareMonth(Date st, Date end) {
		int y = Math.abs((getYear(end) < 0 ? 0 : getYear(end))
				- (getYear(st) < 0 ? 0 : getYear(st)));
		int m = 0;
		if (y > 0) {
			y--;
			m = Math.abs(12 - getMonth(st) + getMonth(end));
		} else {
			m = Math.abs(getMonth(end) - getMonth(st));
		}
		return (y * 12) + m;
	}

	/**
	 * 计算两个日期相差的毫秒数
	 * 
	 * @param start
	 *            启始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public static long compare(Date start, Date end) {
		if (start != null && end != null) {
			return end.getTime() - start.getTime();
		}
		return 0l;
	}

	/**
	 * 判断给的日期，是否是当前的前一天以及更早的日期，若是，返回true，否则返回false
	 * 
	 * @param date
	 * @return
	 */
	public static boolean compareDate(Date date) {
		if (date != null) {
			return date.before(stringToDate(doFormatDate(new Date(), false)));
		}
		return false;
	}

	/**
	 * 自定义格式化日期输出
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String doFormatDate(Date date, String format) {
		if (date == null) {
			return null;
		}
		return (new SimpleDateFormat(format)).format(date);
	}

	public static Date strToDate(String strDate, String formate) {
		SimpleDateFormat formatter = new SimpleDateFormat(formate);
		Date strtodate = null;

		try {
			strtodate = formatter.parse(strDate);
		} catch (ParseException e) {
			logger.error("strToDate错误-异常信息：", e);
			return null;
		}
		return strtodate;
	}

	/**
	 * ｛格式化时间字符串成指定格式｝
	 * 
	 * <pre>
	 * yyyyMMddHHmmss,yyyyMMdd,yyyyMM以及中间含有"-"或":"
	 * </pre>
	 * 
	 * @param strDate
	 *            时间字符串
	 * @param formate
	 *            格式
	 */
	public static String formatStr(String strDate, String formate) {
		String sourceDateStr = formatDS(strDate);
		String sourceFormat = null;
		int dateLength = sourceDateStr.length();
		if (dateLength == 14) {
			sourceFormat = DATESTR_FORMATTER_GA;
		} else if (dateLength == 8) {
			sourceFormat = DATE;
		} else if (dateLength == 6) {
			sourceFormat = DATE_YEAR_MONTH;
		}
		return formatDateStr(strDate, sourceFormat, formate);
	}

	/**
	 * 格式化日期字符串.
	 * 
	 * @param strDate
	 * @param sourceFormat
	 * @param targetFormate
	 * @return
	 */
	public static String formatDateStr(String strDate, String sourceFormat,
                                       String targetFormate) {
		Date date = strToDate(strDate, sourceFormat);
		return doFormatDate(date, targetFormate);
	}

	/**
	 * 对日期进行格式化，格式化后的样式：YYYY-MM-DD/YYYY-MM-DD HH:MM:SS
	 * 
	 * @param date
	 *            要进行格式化的日期
	 * @param b
	 *            为True时，返回长格式的，为Falsh时返回短格式的
	 * @return
	 */
	public static String doFormatDate(Date date, boolean b) {
		if (date == null) {
			return null;
		}
		if (b) {
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
		} else {
			return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
		}
	}

	/**
	 * 将字符串格式的日期转为日期类型，如果不能正确转换则返回null，<br>
	 * 如果含有“:”则会按“yyyy-MM-dd HH:mm:ss”来转换，否则按“yyyy-MM-dd”转换
	 * 
	 * @param datestr
	 * @return
	 */
	public static Date stringToDate(String datestr) {
		if (datestr != null && !"".equals(datestr)) {
			SimpleDateFormat sdf;
			if (datestr.indexOf(":") != -1) {
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
			} else if (datestr.indexOf("-") != -1) {
				sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			} else {
				sdf = new SimpleDateFormat("yyyyMM", Locale.CHINA);
			}
			Date date = null;
			try {
				date = sdf.parse(datestr);
			} catch (Exception e) {
				logger.error("将字符串格式的日期转为日期类型错误-异常信息：", e);
			}
			return date;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 获取当前日期
	 * 
	 */
	public static String getDate() {
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		String jssj = matter1.format(dt);
		return jssj;
	}

	/**
	 * 
	 * 获取当月第一天
	 * 
	 * @return
	 */
	public static String getDates() {
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		String jssj = matter1.format(dt);
		String kssj = jssj.substring(0, 8) + "01";
		return kssj;
	}

	/**
	 * 得到当前系统的日期或时间
	 * 
	 * @param b
	 *            为true 时返回详细时间格式，为false时返回日期格式，不含时分秒
	 * @return 当前的日期或时间
	 */
	public static String getDates(boolean b) {
		return doFormatDate(new Date(), b);
	}

	/**
	 * 获取当前的年,如果是-1，则表示错误
	 * 
	 * @return
	 */
	public static int getYear() {
		return getYear(new Date());
	}

	/**
	 * 获取指定日期的年,如果是-1，则表示错误
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return -1;
		}
		return DateToCalendar(date).get(Calendar.YEAR);
	}

	/**
	 * 获取当前月，如果返回"0"，则表示错误
	 * 
	 * @return
	 */
	public static int getMonth() {
		return getMonth(new Date());
	}

	/**
	 * 获取当前月，如果返回"0"，则表示错误
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return 0;
		}
		return DateToCalendar(date).get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当天日,如果返回"0",表示该日期无效或为null
	 * 
	 * @return
	 */
	public static int getDay() {
		return getDay(new Date());
	}

	/**
	 * 取一个日期的日,如果返回"0",表示该日期无效或为null
	 * 
	 * @param da
	 * @return
	 */
	public static int getDay(Date da) {
		if (da == null) {
			return 0;
		}
		return DateToCalendar(da).get(Calendar.DATE);
	}

	/**
	 * 将java.util.Date类型的日期格式转换成java.util.Calendar格式的日期
	 * 
	 * @param dd
	 * @return
	 */
	public static Calendar DateToCalendar(Date dd) {
		Calendar cc = Calendar.getInstance();
		cc.setTime(dd);
		return cc;
	}

	/**
	 * 将一个长整型数据转为日期
	 * 
	 * @param datenum
	 * @return
	 */
	public static Date longToDate(long datenum) {
		Calendar cc = Calendar.getInstance();
		cc.setTimeInMillis(datenum);
		return cc.getTime();
	}

	/**
	 * 将一个长整型数据转为日期格式的字符串
	 * 
	 * @param datenum
	 * @return
	 */
	public static String longToDateString(long datenum) {
		return doFormatDate(longToDate(datenum), true);
	}

	/**
	 * 得到给定日期的前一个周日的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getUpWeekDay(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar cc = Calendar.getInstance();
			cc.setTime(date);
			int week = cc.get(Calendar.DAY_OF_WEEK);
			return DateUtils.addDay(date, (1 - week));
		}
	}

	/**
	 * 得到给定日期所在周的周一日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonday(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar cc = Calendar.getInstance();
			cc.setTime(date);
			int week = cc.get(Calendar.DAY_OF_WEEK);
			return DateUtils.addDay(date, (2 - week));
		}
	}

	/**
	 * 得到指定日期所在的周（1-7），惹指定的日期不存在，则返回“-1”
	 * 
	 * @param date
	 * @return -1 or 1-7
	 */
	public static int getWeek(Date date) {
		if (date == null) {
			return -1;
		} else {
			Calendar cc = Calendar.getInstance();
			cc.setTime(date);
			int week = cc.get(Calendar.DAY_OF_WEEK);
			if (week == 1) {
				week = 7;
			} else {
				week--;
			}
			return week;
		}
	}

	/**
	 * 产生随机数
	 * 
	 * @param lo
	 * @return
	 */
	public static String getRandNum(int lo) {
		if (lo < 1) {
			lo = 4;
		}
		StringBuffer temp = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < lo; i++) {
			temp.append(String.valueOf(rand.nextInt(10)));
		}
		return temp.toString();
	}

	/**
	 * 产生文件函数名，以当然日期+4位随机码为主
	 */
	public static String getDataName() {
		return DateUtils.doFormatDate(new Date(), "yyyyMMddHHmmss")
				+ getRandNum(4);
	}

	/**
	 * 将DATE转为数据库的Timestamp类型
	 * 
	 * @param dt
	 * @return
	 */
	public static Timestamp dateToTime(Date dt) {
		if (dt == null) {
			return null;
		}
		return new Timestamp(dt.getTime());
	}

	/**
	 * method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
	 * 
	 * @param dateString
	 *            需要转换为timestamp的字符串
	 * @return dataTime timestamp
	 */
	@SuppressWarnings("static-access")
	public static Timestamp string2Time(String dateString)
			throws java.text.ParseException {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS",
				Locale.ENGLISH);// 设定格式
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
		// Locale.ENGLISH);
		dateFormat.setLenient(false);
		// 我做这块的时候下边的不对，后来我把dateFormat后边加上了.getDateInstance()就好了
		// java.util.Date timeDate = dateFormat.parse(dateString);//util类型
		Date ywybirt = dateFormat.getDateInstance().parse(dateString);// util类型
		Timestamp dateTime = new Timestamp(ywybirt.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
		return dateTime;
	}

	/**
	 * 将日期格式转为java.sql.Date
	 * 
	 * @param de
	 * @return
	 */
	public static java.sql.Date dateToSqlDate(Date de) {
		return new java.sql.Date(de.getTime());
	}

	/**
	 * 格式化日期字符串 yyyymmddhh24miss
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDS(String date) {
		if (date == null) {
			return "";
		}
		return date.replace("-", "").replace(":", "").replace(" ", "");
	}

	/**
	 * 将公安部的时间格式转为日期格式
	 * 
	 * @param datestr
	 * @return
	 */
	public static Date strTdate(String datestr) {
		if (datestr != null && !"".equals(datestr)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.CHINA);
			Date date = null;
			try {
				date = sdf.parse(datestr);
			} catch (Exception e) {
				logger.error("将公安部的时间格式转为日期格式错误-异常信息：", e);
			}
			return date;
		} else {
			return null;
		}
	}

	/**
	 * 以公安部的日期格式返回当前系统时间
	 * 
	 * @return
	 */
	public static String getGabDate() {
		return doFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String str2StrDate(String datestr) {
		Date dd = strTdate(datestr);
		if (dd != null) {
			return DateUtils.doFormatDate(dd, true);
		}
		return null;
	}

	/**
	 * 获得往年同期
	 * 
	 * @param datetime
	 * @param year
	 * @return
	 */
	public static String DateAddYear(String datetime, int year) {
		String rtnVal = "";

		Date date = null;
		date = addYear(strTdate(datetime), year);
		rtnVal = formatDS(doFormatDate(date, true));

		return rtnVal;
	}

	/**
	 * 字符串日期加月
	 * 
	 * @param date
	 * @param month
	 * @param time
	 * @return
	 */
	public static String DateAddMonth(String date, int month, String time) {
		String rtnVal = "";

		date = formatDS(date);

		Date datetime = null;
		String temp = formatDS(date) + time;
		datetime = addMonth(strTdate(temp), month);
		temp = doFormatDate(datetime, true);
		rtnVal = formatDS(temp);

		return rtnVal;
	}

	/**
	 * 
	 * 将公安部的14位的时间字符串转换成"YYYY-MM-DD hh:mm:ss"
	 * 
	 * @param time
	 *            14位的时间字符串 如20130405210204
	 * @return YYYY-MM-DD hh:mm:ss格式的时间字符串
	 */
	public static String timeFormate(String time) {
		if (time == null || time.length() < 14) {
			return time;
		} else {
			StringBuilder str = new StringBuilder();
			str.append(time.substring(0, 4)).append("-")
					.append(time.substring(4, 6)).append("-")
					.append(time.substring(6, 8)).append(" ")
					.append(time.substring(8, 10)).append(":")
					.append(time.substring(10, 12)).append(":")
					.append(time.substring(12, 14));
			return str.toString();
		}
	}

	/**
	 * 
	 * 将14为字符串转换为“yyyy-mm-dd”格式的时间字符
	 * 
	 * @param time
	 * @return
	 */
	public static String datetimeFormate(String time) {
		if (StringUtils.isBlank(time)) {
			return time;
		} else if (time.length() == 14 || time.length() == 8) {
			StringBuilder str = new StringBuilder();
			str.append(time.substring(0, 4)).append("-")
					.append(time.substring(4, 6)).append("-")
					.append(time.substring(6, 8));
			return str.toString();
		} else {
			return time;
		}
	}

	/**
	 * 
	 * 将14为字符串转换为“yyyy-mm-dd HH:MM”格式的时间字符
	 * 
	 * @param time
	 * @return
	 */
	public static String dayHourtimeFormate(String time) {
		if (StringUtils.isBlank(time)) {
			return time;
		} else if (time.length() == 14 || time.length() == 8) {
			StringBuilder str = new StringBuilder();
			str.append(time.substring(0, 4)).append("-")
					.append(time.substring(4, 6)).append("-")
					.append(time.substring(6, 8)).append(" ")
					.append(time.substring(8, 10)).append(":")
					.append(time.substring(10, 12));
			return str.toString();
		} else if (time.length() == 12) {
			StringBuilder str = new StringBuilder();
			str.append(time.substring(0, 4)).append("-")
					.append(time.substring(4, 6)).append("-")
					.append(time.substring(6, 8)).append(" ")
					.append(time.substring(8, 10)).append(":")
					.append(time.substring(10, 12));
			return str.toString();
		} else if (time.length() == 6) {
			StringBuilder str = new StringBuilder();
			str.append(time.substring(0, 4)).append("-")
					.append(time.substring(4, 6));
			return str.toString();
		} else {
			return time;
		}
	}

	/**
	 * 获得当前系统时间的公安部格式
	 * 
	 * @return
	 */
	public static String getCurrentGaDate() {
		return doFormatDate(new Date(), DATESTR_FORMATTER_GA);
	}

	/**
	 * 时间转为字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date date, String format) {
		return doFormatDate(date, format);
	}

	/**
	 * 获取格式化的日期字符串
	 * 
	 * <BR>
	 * G Era 标志符 Text AD <BR>
	 * y 年 Year 1996; 96 <BR>
	 * M 年中的月份 Month July; Jul; 07 <BR>
	 * w 年中的周数 Number 27 <BR>
	 * W 月份中的周数 Number 2 <BR>
	 * D 年中的天数 Number 189 <BR>
	 * d 月份中的天数 Number 10 <BR>
	 * F 月份中的星期 Number 2 <BR>
	 * E 星期中的天数 Text Tuesday; Tue <BR>
	 * a Am/pm 标记 Text PM <BR>
	 * H 一天中的小时数（0-23） Number 0 <BR>
	 * k 一天中的小时数（1-24） Number 24 <BR>
	 * K am/pm 中的小时数（0-11） Number 0 <BR>
	 * h am/pm 中的小时数（1-12） Number 12 <BR>
	 * m 小时中的分钟数 Number 30 <BR>
	 * s 分钟中的秒数 Number 55 <BR>
	 * S 毫秒数 Number 978 <BR>
	 * z 时区 General time zone Pacific Standard Time; PST; GMT-08:00 <BR>
	 * Z 时区 RFC 822 time zone -0800
	 * 
	 * @param date
	 *            日期
	 * @param formatStr
	 *            格式 如：yyyy-MM-dd HH:mm:ss SSS
	 * @return 格式化的日期字符串
	 */
	public static String parseDate(Date date, String formatStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);

		if (date == null) {
			return null;
		} else {
			return dateFormat.format(date);
		}
	}

	/**
	 * ｛指定格式的字符串加N天之后的日期｝
	 * 
	 * @param date
	 *            日期字符串
	 * @param day
	 *            指定在当前日期上加几天
	 * @param formatStr
	 *            字符串日期格式
	 * @return
	 */
	public static String addDate(String date, int day, String formatStr) {
		return DateUtils.dateToStr(
				DateUtils.addDay(DateUtils.strToDate(date, formatStr), day),
				formatStr);
	}

	/**
	 * 
	 * 两日期之差
	 * 
	 * @param beginData
	 *            开始时间
	 * @param endData
	 *            结束时间
	 * @return
	 */
	public static String getDatacompare(String beginData, String endData) {
		long time = compare(
				DateUtils.strTdate(DateUtils.timeFormate(beginData)),
				DateUtils.strTdate(DateUtils.timeFormate(endData)));
		// long diffSeconds = time / 1000 % 60;
		long diffMinutes = time / (60 * 1000) % 60;
		long diffHours = time / (60 * 60 * 1000) % 24;
		long diffDays = time / (24 * 60 * 60 * 1000);
		return diffDays + "天" + diffHours + "小时" + diffMinutes + "分";
	}

	/**
	 * 严格校验日期字符串
	 * 
	 * @param date
	 *            日期字符串
	 * @param format
	 *            日期格式
	 */
	public static void dateCheck(String date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			// 严格校验
			sdf.setLenient(false);
			sdf.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不对");
		}
	}

	public static String dateDiff(String date) {
		Calendar sysdate = Calendar.getInstance();
		Calendar date1 = Calendar.getInstance();
		Date b = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			b = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		date1.setTime(b);
		long systime = sysdate.getTimeInMillis();
		long time2 = date1.getTimeInMillis();
		long tempTime = systime - time2;
		int day = (int) ((tempTime) / (3600 * 24 * 1000));
		// int hour=(int) (tempTime/3600000);
		return String.valueOf(day);
	}

	/**
	 * ｛格式化时间字符串成指定格式｝
	 * 
	 * <pre>
	 * yyyyMMddHHmmss,yyyyMMdd,yyyyMM以及中间含有"-"或":"
	 * </pre>
	 * 
	 * @param strDate
	 *            时间字符串
	 */
	public static String formatStrTime(String strDate) {
		String sourceDateStr = formatDS(strDate);
		int dateLength = sourceDateStr.length();
		if (dateLength == 14) {
			String newTime = sourceDateStr.substring(0, 12);
			return dayHourtimeFormate(newTime);
		} else if (dateLength == 12) {
			return dayHourtimeFormate(sourceDateStr);
		}
		return strDate;
	}

	/**
	 * 
	 * 获得当年的日期
	 * 
	 */
	public static String getDateYear(Date dt) {
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyyMMdd");
		String jssj = matter1.format(dt);
		String kssj = jssj.substring(0, 4);
		return kssj;
	}

	/**
	 * 
	 * 获得当年的第一天1月1日
	 * 
	 * @return
	 */
	public static String getFirstDayOfYear(Date dt) {
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyyMMdd");
		String jssj = matter1.format(dt);
		String kssj = jssj.substring(0, 4) + "0101";
		;
		return kssj;
	}

	/**
	 * 
	 * 获得当月的月份
	 * 
	 * @return
	 */
	public static String getDateMonth(Date dt) {
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyyMMdd");
		String jssj = matter1.format(dt);
		String kssj = jssj.substring(4, 6);
		return kssj;
	}

	/**
	 * 
	 * 获取当月第一天
	 * 
	 * @return
	 */
	public static String getDateMonthDay(Date dt) {
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyyMMdd");
		String jssj = matter1.format(dt);
		String kssj = jssj.substring(0, 6) + "01";
		return kssj;
	}

	/**
	 * 
	 * 获得下个季度的月份
	 * 
	 * @return
	 */
	public static String getDateNextJdMonth(Date dt) {
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyyMMdd");
		String jssj = matter1.format(dt);
		String kssj = jssj.substring(4, 6);
		String njd = String.valueOf(Integer.valueOf(kssj) + 2);
		return njd;
	}

	/**
	 * 
	 * 获得当前日期
	 * 
	 * @return
	 */
	public static String getDate(Date dt) {
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyyMMdd");
		String jssj = matter1.format(dt);
		return jssj;
	}

	/**
	 * 得到给定日期所在周的周一日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMondaySpecial(Date date) {
		if (date == null) {
			return null;
		} else {
			int week = getWeek(date);
			return DateUtils.addDay(date, (1 - week));
		}
	}

	/**
	 * ｛获得年月的最后一天｝
	 * 
	 * @param yyyymm
	 *            201708
	 * @return String 20170831
	 */
	public static String getLastDayMonth(String yyyymm) {
		if (StringUtils.isBlank(yyyymm)) {
			return "";
		}
		Date nd = stringToDate(yyyymm.substring(0, 4) + "-"
				+ yyyymm.substring(4, 6) + "-01");
		Date date = addDay(addMonth(nd, 1), -1);
		return dateToStr(date, "");
	}
}
