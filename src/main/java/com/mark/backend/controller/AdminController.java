package com.mark.backend.controller;

import com.mark.backend.dto.RemarkDto;
import com.mark.backend.model.Admin;
import com.mark.backend.mysql.po.Association;
import com.mark.backend.mysql.po.Book;
import com.mark.backend.mysql.po.Category;
import com.mark.backend.mysql.po.Group;
import com.mark.backend.mysql.po.User;
import com.mark.backend.service.IAssociationService;
import com.mark.backend.service.IBookService;
import com.mark.backend.service.ICategoryService;
import com.mark.backend.service.ICloudUploadService;
import com.mark.backend.service.IGroupService;
import com.mark.backend.service.IRemarkService;
import com.mark.backend.service.IUserService;
import com.mark.backend.service.impl.WeixinService;
import com.mark.backend.utils.MarkUtils;
import com.mark.backend.utils.RemarkDataExcelView;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private WeixinService wxService;
    @Resource
    private IGroupService groupService;
    @Resource
    private IBookService bookService;
    @Resource
    private IAssociationService associationService;
    @Resource
    private ICloudUploadService uploadService;
    @Resource
    private IUserService userSerivce;
    @Resource
    private ICategoryService categoryService;

    @Resource
    private IRemarkService remarkService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Admin admin, Model model, HttpServletRequest request,
                        HttpServletResponse response) {
        if ("imark".equals(admin.getUsername())
                && "root".equals(admin.getPassword())) {
            Cookie c = new Cookie("mark.com", "loged");
            String contextPath = request.getContextPath();
            c.setPath(contextPath);
            response.addCookie(c);
            return "admin/dashboard";
        }

        return "redirect:/admin/index";
    }

    /**
     * ---------------------用户controller------------------------
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        List<User> userList = userSerivce.getUserList(null);
        model.addAttribute("userlist", userList);
        return "admin/users";
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public String users(Model model, String nickName) {
        List<User> userList = userSerivce.getUserList(nickName);
        model.addAttribute("userlist", userList);
        return "admin/users";
    }

    /**
     * ---------------------社群相关controller------------------------
     */
    @RequestMapping(value = "/association", method = RequestMethod.GET)
    public String associationList(Model model) {
        List<Association> list = associationService.getAllList(null);
        model.addAttribute("list", list);
        return "admin/association";
    }

    @RequestMapping(value = "/association/edit", method = RequestMethod.GET)
    public String association(Model model, Long associationId) {
        if (associationId != null) {
            Map<String, Object> map = associationService
                    .getAssociationById(associationId);
            model.addAttribute("association", map.get("association"));
            model.addAttribute("pic", map.get("pic"));
        }
        return "admin/association_edit";
    }

    @RequestMapping(value = "/association/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Object associationSave(Model model, Association association,
                           String pictureUrl) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer i = associationService.editAssociation(association, pictureUrl);
        if (i > 0) {
            map.put("success", "true");
        }
        return map;
    }

    @RequestMapping(value = "/association/delete", method = RequestMethod.GET)
    public String associationSave(Model model, Long associationId) {
        associationService.deleteByAssociationId(associationId);
        return "redirect:/admin/association";
    }

    /**
     * ---------------------修改微信按钮相关controller------------------------
     */
    @RequestMapping(value = "/menu/create", method = RequestMethod.POST)
    public String menuCreate(String jsonStr, Model model) {
        String responseStr = wxService.createMenu(jsonStr);
        model.addAttribute("responseStr", responseStr);
        return "admin/menu";
    }

    /**
     * ---------------------申请相关controller------------------------
     */
    @RequestMapping(value = "/applygroup", method = RequestMethod.GET)
    public String applyGroup(Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        // 查询申请状态的小组
        params.put("status", "applygroup");
        List<Group> groupList = groupService.getAllGroup(params);
        model.addAttribute("groupList", groupList);
        // 显示通过按钮
        model.addAttribute("apply", "apply");
        return "admin/group";
    }

    /**
     * ---------------------书籍相关controller------------------------
     */
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String book(Model model, String bookName) {
        List<Book> bookList = bookService.getBookList(bookName);
        model.addAttribute("bookList", bookList);
        return "admin/book";
    }

    @RequestMapping(value = "/book/search", method = RequestMethod.POST)
    public String bookSearch(Model model, String bookName) {
        List<Book> bookList = bookService.getBookList(bookName);
        model.addAttribute("bookList", bookList);
        return "admin/book";
    }

    @RequestMapping(value = "/book/edit", method = RequestMethod.GET)
    public String bookEdit(Model model, Long bookId) {
        if (bookId != null) {
            Book book = bookService.getBookById(bookId);
            model.addAttribute("book", book);
        }
        return "/admin/book_edit";
    }

    @RequestMapping(value = "/book/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Object bookSave(Model model, Book book) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long bookId = bookService.saveBook(book);
        if (bookId > 0) {
            map.put("bookId", bookId);
            map.put("success", "true");
        } else {
            map.put("success", "false");
        }
        return map;
    }

    @RequestMapping(value = "/book/delete", method = RequestMethod.GET)
    public Object bookSave(Model model, Long bookId) {
        bookService.deleteByBookId(bookId);
        return "redirect:/admin/book";
    }

    /**
     * 搜索豆瓣
     */
    @RequestMapping(value = "/book/searchadouban", method = RequestMethod.POST)
    public
    @ResponseBody
    Object searchaDouban(Model model, String searchname) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Book> bookList = MarkUtils.getDoubanBookList(searchname, 5);
        resultMap.put("success", "true");
        resultMap.put("booklist", bookList);
        return resultMap;
    }

    /** ---------------------小组相关controller------------------------ */
    /**
     * 条件获取小组列表
     */
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String groups(Model model, Long associationId, Long categoryId) {
        Map<String, Object> params = new HashMap<String, Object>();
        // 查询申请状态的小组
        params.put("status", "group");
        // 获取社群
        if (associationId != null) {
            Association association = (Association) associationService
                    .getAssociationById(associationId).get("association");
            model.addAttribute("association", association);
        }
        // 获取类别
        if (categoryId != null) {
            params.put("categoryId", categoryId);
            Category category = categoryService.getCategoryById(categoryId);
            model.addAttribute("category", category);
        }

        List<Group> groupList = groupService.getAllGroup(params);
        model.addAttribute("groupList", groupList);
        return "admin/group";
    }

    @RequestMapping(value = "/group/edit", method = RequestMethod.GET)
    public String editGroup(Model model, Long groupId, Long associationId,
                            Long categoryId) {
        if (groupId != null) {
            Group group = groupService.getGroupInfo(groupId);
            model.addAttribute(group);
        } else {
            Group group = new Group();
            model.addAttribute(group);
        }
        if (associationId != null) {
            model.addAttribute("associationId", associationId);
        }
        if (categoryId != null) {
            model.addAttribute("categoryId", categoryId);
        }
        return "admin/group_edit";
    }

    @RequestMapping(value = "/group/save", method = RequestMethod.POST)
    public String saveGroup(Model model, Group group, Long associationId,
                            Long categoryId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("group", group);
        params.put("associationId", associationId);
        params.put("categoryId", categoryId);
        // 新建小组默认通过批准
        params.put("approve", "approve");
        groupService.saveGroup(params);
        return "redirect:/admin/group";
    }

    @RequestMapping(value = "/group/approve", method = RequestMethod.GET)
    public String approveGroup(Model model, Group group) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("group", group);
        params.put("approve", "approve");
        groupService.approveGroup(params);
        return "redirect:/admin/group";
    }

    @RequestMapping(value = "/group/delete", method = RequestMethod.GET)
    public String deleteGroup(Model model, Long groupId) {
        groupService.deleteGroupById(groupId);
        return "redirect:/admin/group";
    }

    /**
     * ---------------------上传图片controller------------------------
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    Object upload(MultipartFile pictureUrl) {
        Map<String, Object> map = new HashMap<String, Object>();
        String picUrl = "";
        if (pictureUrl != null) {
            picUrl = uploadService.upload(pictureUrl, "");
        }
        if (picUrl != null) {
            map.put("status", "true");
            map.put("pictureUrl", picUrl);
        } else {
            map.put("status", "false");
        }
        return map;
    }

    /**
     * ---------------------类别controller------------------------
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String getAssociaCategory(Model model, Long associationId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("associationId", associationId);
        List<Category> categoryList = categoryService.getCategoryList(params);
        model.addAttribute("associationId", associationId);
        model.addAttribute("categorylist", categoryList);
        return "admin/category";
    }

    @RequestMapping(value = "/category/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Object saveCategory(Model model, Long associationId, Long categoryId,
                        String categoryName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("associationId", associationId);
        params.put("categoryId", categoryId);
        params.put("categoryName", categoryName);
        Long i = categoryService.saveCategory(params);
        if (i > 0) {
            params.clear();
            params.put("success", "true");
        }
        return params;
    }

    @RequestMapping(value = "/category/delete", method = RequestMethod.GET)
    public String deleteCategory(Model model, Long categoryId,
                                 Long associationId) {
        categoryService.deleteByCategoryId(categoryId);
        return "redirect:/admin/category?associationId=" + associationId;
    }


    @RequestMapping(value = "/remark", method = RequestMethod.GET)
    public String remark(Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        // 查询申请状态的小组
        params.put("status", "group");
        List<Group> groupList = groupService.getAllGroup(params);
        model.addAttribute("groupList", groupList);
        return "admin/remark";
    }

    @RequestMapping(value = "/remark/group", method = RequestMethod.GET)
    public String remarkOfGroup(Model model, @RequestParam Long groupId) {
        Group group = groupService.getGroupInfo(groupId);
        if (group != null) {
            model.addAttribute("group", group);
            String bookIdFk = group.getBookIdFk();
            if (!StringUtils.isEmpty(bookIdFk)) {
                Book book = bookService.getBookById(Long.parseLong(bookIdFk));
                model.addAttribute("book", book);
            }
        }
        Map<String, Object> groupRemarks = remarkService.getGroupRemark(groupId);
        if (groupRemarks != null) {
            model.addAttribute("groupRemarks", groupRemarks);
        }
        List<User> userList = userSerivce.getUserList(null);
        List<User> filteredList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userList)) {
            for (User u : userList) {
                if (!StringUtils.isEmpty(u.getNickname())) {
                    filteredList.add(u);
                }
            }
        }
        model.addAttribute("userList", filteredList);

        return "admin/group_remark";
    }

    /**
     * 小组成长报告
     */
    @RequestMapping(value = "/remark/report_page", method = RequestMethod.GET)
    public String remarkReportsPage(Model model, @RequestParam Long groupId) throws ParseException {
        Group group = groupService.getGroupInfo(groupId);
        if (group != null) {
            model.addAttribute("group", group);
            String bookIdFk = group.getBookIdFk();
            if (!StringUtils.isEmpty(bookIdFk)) {
                Book book = bookService.getBookById(Long.parseLong(bookIdFk));
                model.addAttribute("book", book);
            }
        }
        Map<String, Object> groupRemarks = remarkService.getGroupRemark(groupId);
        if (groupRemarks != null) {
            model.addAttribute("groupRemarks", groupRemarks);
        }
        List<User> userList = userSerivce.getUserList(null);
        List<User> filteredList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userList)) {
            for (User u : userList) {
                if (!StringUtils.isEmpty(u.getNickname())) {
                    filteredList.add(u);
                }
            }
        }
        return "admin/remark_reports";
    }


    /**
     * 导出一个小组的成长报告
     */
    @RequestMapping(value = "/remark/reports", method = RequestMethod.GET)
    public String remarkReports(Model model, @RequestParam(required = false) String startTime,
                                @RequestParam(required = false) String endTime,
                                @RequestParam Long groupId) throws ParseException {
        // Group book
        Group group = groupService.getGroupInfo(groupId);
        if (group != null) {
            model.addAttribute("group", group);
            String bookIdFk = group.getBookIdFk();
            if (!StringUtils.isEmpty(bookIdFk)) {
                Book book = bookService.getBookById(Long.parseLong(bookIdFk));
                model.addAttribute("book", book);
            }
        }


        // Group Remarks
        Map<String, Object> groupRemarks = remarkService.getGroupRemark(groupId);
        if (groupRemarks != null) {
            model.addAttribute("groupRemarks", groupRemarks);
        }
        List<User> userList = userSerivce.getUserList(null);
        List<User> filteredList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userList)) {
            for (User u : userList) {
                if (!StringUtils.isEmpty(u.getNickname())) {
                    filteredList.add(u);
                }
            }
        }


        // 小组总人数
        int totalUsers = userList == null ? 0 : userList.size();
        model.addAttribute("totalUsers", totalUsers);

        Date start = null;
        Date end = null;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        if (startTime == null) {
            start = getTodayStartTime();
        } else {
            start = sdf.parse(startTime);
        }
        if (endTime == null) {
            end = new Date();
        } else {
            end = sdf.parse(endTime);
        }
        if (end.compareTo(start) <= 0) {
            return null;
        }
        model.addAttribute("duaritionDates", MarkUtils.getDatesBwtweenInString(start, end));

        List<RemarkDto> timeOrderList = null;
        List<RemarkDto> hotOrderList = new ArrayList<>();
        if (groupRemarks != null) {
            timeOrderList = (ArrayList<RemarkDto>) groupRemarks.get("timeorderlist");

            List<RemarkDto> hotlistNotFiltered = (ArrayList<RemarkDto>) groupRemarks.get("hotlist");
            if (hotlistNotFiltered != null) {
                for (RemarkDto remarkDto : hotlistNotFiltered) {
                    if (start.compareTo(remarkDto.getCreateTime()) <= 0 && end.compareTo(remarkDto.getCreateTime()) >= 0) {
                        hotOrderList.add(remarkDto);
                    }
                }
            }
        }

        Map<String, Integer> remarkedTimes = new HashMap<>();
        Map<String, Integer> userPunchDateMap = new HashMap<>();

        if (!CollectionUtils.isEmpty(timeOrderList)) {
            Set<Long> userIdSet = new HashSet<>();

            int totalCharacters = 0;
            int totalRemarks = 0;
            for (RemarkDto remarkDto : timeOrderList) {
                Date remarkCreateTime = remarkDto.getCreateTime();
                if (start.compareTo(remarkCreateTime) <= 0 && end.compareTo(remarkCreateTime) >= 0) {
                    totalRemarks += 1;
                    userIdSet.add(remarkDto.getUserId());
                    totalCharacters += remarkDto.getComment().length();
                    if (StringUtils.isEmpty(remarkDto.getUserName())) {
                        continue;
                    } else {
                        if (remarkedTimes.get(remarkDto.getUserName()) == null) {
                            remarkedTimes.put(remarkDto.getUserName(), 1);
                        } else {
                            remarkedTimes.put(remarkDto.getUserName(), remarkedTimes.get(remarkDto.getUserName()) + 1);
                        }
                    }
                }
            }
            model.addAttribute("userPunchTimesMap", sortMap(remarkedTimes, true));
            model.addAttribute("totalRemarks", totalRemarks);
            model.addAttribute("totalUsersRemarked", userIdSet.size());
            model.addAttribute("totalCharacters", totalCharacters);
            // model.addAttribute("punchMap", sortedMap);
        }

        if (!CollectionUtils.isEmpty(hotOrderList)) {
            model.addAttribute("hotOrderTopThree", hotOrderList.subList(0, 3));
        }

        // 获取用户连续打卡信息
        Map<String, Object> params = new HashMap<>();
        params.put("startTime", start.getTime());
        params.put("endTime", end.getTime());
        params.put("groupId", groupId);
        Map<String, Integer> continuePunchTimesMap = new HashMap<>();
        Map<String, List<Date>> notPunchDates = new HashMap<>();
        for (User u : userList) {
            if (u.getId() != null) {
                params.put("userId", u.getId());
                int continuePunchTimes = remarkService.getContinuePunchInfoBetween(params);
                List<Date> notPunchDatesList = remarkService.getNotPunchDates(params);
                if (StringUtils.isEmpty(u.getNickname())) {
                    continue;
                }
                notPunchDates.put(u.getNickname(), notPunchDatesList);
                continuePunchTimesMap.put(u.getNickname(), continuePunchTimes);
            }
        }


        Map<String, Integer> sortedContinuePunchTimesMap = sortMap(continuePunchTimesMap, true);
        Map<String, List<String>> sortedNotPunchDates = sortMapList(notPunchDates, true);

        if (sortedContinuePunchTimesMap != null) {
            int i = 0;
            Iterator<String> iterator = sortedContinuePunchTimesMap.keySet().iterator();
            while (iterator.hasNext()) {
                i++;
                iterator.next();
                if (i > 5) {
                    iterator.remove();
                }
            }
        }
        if (sortedNotPunchDates != null) {
            int i = 0;
            Iterator<String> iterator = sortedNotPunchDates.keySet().iterator();
            while (iterator.hasNext()) {
                i++;
                iterator.next();
                if (i > 5) {
                    iterator.remove();
                }
            }
        }

        model.addAttribute("continuePunchMap", sortedContinuePunchTimesMap);
        model.addAttribute("notPunchDatesMap", sortedNotPunchDates);


        return "admin/remark_reports";

    }

    private static Map sortMap(Map oldMap, final boolean desc) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> arg0,
                               Map.Entry<String, Integer> arg1) {
                if (desc) {
                    return arg1.getValue() - arg0.getValue();

                } else {
                    return arg0.getValue() - arg1.getValue();
                }
            }
        });
        Map newMap = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }

    private static Map<String, List<String>> sortMapList(Map<String, List<Date>> oldMap, final boolean desc) {
        ArrayList<Map.Entry<String, List<Date>>> list = new ArrayList<>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, List<Date>>>() {

            @Override
            public int compare(Map.Entry<String, List<Date>> o1, Map.Entry<String, List<Date>> o2) {
                int o1Size = o1.getValue().size();
                int o2Size = o2.getValue().size();
                if (desc) {
                    if (o1Size < o2Size) {
                        return 1;
                    } else if (o1Size == o2Size) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    if (o1Size > o2Size) {
                        return 1;
                    } else if (o1Size == o2Size) {
                        return 0;
                    } else {
                        return -1;
                    }
                }

            }
        });
        Map<String, List<String>> newMap = new LinkedHashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < list.size(); i++) {
            List<Date> dates = list.get(i).getValue();
            List<String> strings = new ArrayList<>();
            for (Date d : dates) {
                strings.add(simpleDateFormat.format(d));

            }
            newMap.put(list.get(i).getKey(), strings);
        }
        return newMap;
    }

    /**
     * 导出一个小组的书评
     */
    @RequestMapping(value = "/remark/download", method = RequestMethod.GET)
    public ModelAndView remarkDownload(@RequestParam(required = false) String startTime,
                                       @RequestParam(required = false) String endTime,
                                       @RequestParam(required = false) Long userId,
                                       @RequestParam Long groupId) throws ParseException {

        Date start = null;
        Date end = null;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        if (startTime == null) {
            start = getTodayStartTime();
        } else {

            start = sdf.parse(startTime);
        }
        if (endTime == null) {
            end = new Date();
        } else {
            end = sdf.parse(endTime);
        }
        if (end.compareTo(start) <= 0) {
            return null;
        }
        Map<String, Object> groupRemark = remarkService.getGroupRemark(groupId);
        List<RemarkDto> remarkList = null;
        List<RemarkDto> matchedData = new ArrayList<>();
        if (groupRemark != null) {
            remarkList = (ArrayList<RemarkDto>) groupRemark.get("timeorderlist");
        }
        if (!CollectionUtils.isEmpty(remarkList)) {
            for (RemarkDto remark : remarkList) {
                Date remarkCreateTime = remark.getCreateTime();
                if (remarkCreateTime != null) {
                    if (userId != null && userId == -1) {
                        if (start.compareTo(remarkCreateTime) <= 0 && end.compareTo(remarkCreateTime) >= 0) {
                            matchedData.add(remark);
                        }
                    } else {
                        if (start.compareTo(remarkCreateTime) <= 0 && end.compareTo(remarkCreateTime) >= 0 && remark.getUserId().equals(userId)) {
                            matchedData.add(remark);
                        }
                    }

                }
            }
        }
        return new ModelAndView(new RemarkDataExcelView(), "remarkData", matchedData);

    }


    private Date getTodayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

}
