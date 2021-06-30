package by.ilya.catalog.endpoint;

import by.ilya.catalog.domain.Manager;
import by.ilya.catalog.dto.admin.AuthorDTO;
import by.ilya.catalog.dto.admin.ContestDTO;
import by.ilya.catalog.dto.admin.LinkDBDTO;
import by.ilya.catalog.dto.admin.ManagerDTO;
import by.ilya.catalog.dto.admin.SubGovernanceDTO;
import by.ilya.catalog.dto.admin.SubmissionDTO;
import by.ilya.catalog.facade.admin.AdminAuthorFacade;
import by.ilya.catalog.facade.admin.AdminContestFacade;
import by.ilya.catalog.facade.admin.AdminManagerPageFacade;
import by.ilya.catalog.facade.admin.AdminSubGovernanceFacade;
import by.ilya.catalog.facade.admin.AdminSubmissionFacade;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;


@Controller
@RequestMapping("admin")
public class AdminEndpoint {

    private AdminManagerPageFacade adminManagerPageFacade;
    private AdminSubGovernanceFacade adminSubGovernanceFacade;
    private AdminContestFacade adminContestFacade;
    private AdminSubmissionFacade adminSubmissionFacade;
    private AdminAuthorFacade adminAuthorFacade;

    @GetMapping("/")
    public String adminIndex(Model model) {
        model.addAttribute("managers", adminManagerPageFacade.getList());
        return "admin/manager/managers-list";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("managers", adminManagerPageFacade.getList());
        return "admin/manager/managers-list";
    }

    @GetMapping("/currentUserName")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/managers/checkUserName")
    @ResponseBody
    public Boolean checkUserName(@RequestParam(value = "userName") String userName){
        return adminManagerPageFacade.isUserNameExist(userName);
    }

    @GetMapping("/managers/openAdd")
    public String openAddPage(Model model){
        return "admin/manager/manager-create";
    }

    @GetMapping("/managers/openEdit")
    public String openEditPage(@RequestParam(value = "id") long id, Model model, Principal principal){
        ManagerDTO manager = adminManagerPageFacade.getById(id);
        if (manager == null || manager.getNickName().equals(principal.getName())){
            model.addAttribute("errorMessage", "You cannot edit current user.");
            return "error";
        }
        model.addAttribute("manager", manager);

        return "admin/manager/manager-edit";
    }

    @GetMapping("/managers/list")
    public String getManagersList(Model model) {
        model.addAttribute("managers", adminManagerPageFacade.getList());
        return "admin/manager/managers-list";
    }

    @PostMapping("/managers/add")
    public String createManager(@ModelAttribute Manager manager, Model model){
        model.addAttribute("managers", adminManagerPageFacade.create(manager));
        return "admin/manager/managers-list";
    }

    @PostMapping("/managers/edit")
    public String editManager(@ModelAttribute ManagerDTO managerDTO, Model model){
        try {
            model.addAttribute("managers", adminManagerPageFacade.edit(managerDTO));
        } catch (NotFoundException e) {
            return "error";
        }
        return "admin/manager/managers-list";
    }

    @GetMapping("/managers/delete")
    public String deleteManager(@RequestParam(value = "id") long id, Model model, Principal principal){
        model.addAttribute("managers", adminManagerPageFacade.delete(id, principal.getName()));
        return "admin/manager/managers-list";
    }

    @GetMapping("/subGovernances/list")
    public String subGovernancesList(Model model){
        model.addAttribute("subGovernances", adminSubGovernanceFacade.getList());
        return "admin/subGovernance/subgov-list";
    }

    @GetMapping("/subGovernances/openAdd")
    public String openSubGovAddPage(Model model){
        return "admin/subGovernance/subgov-create";
    }

    @GetMapping("/subGovernances/openEdit")
    public String openSubGovEditPage(@RequestParam(value = "id") long id, Model model, Principal principal){
        SubGovernanceDTO subGovernance = adminSubGovernanceFacade.getById(id);
        if (subGovernance == null){
            model.addAttribute("errorMessage", "You cannot edit current user.");
            return "error";
        }
        model.addAttribute("subGov", subGovernance);

        return "admin/subGovernance/subgov-edit";
    }

    @PostMapping("/subGovernances/add")
    public String createSubGovernance(@ModelAttribute SubGovernanceDTO subGovernance, Model model){
        adminSubGovernanceFacade.create(subGovernance);
        model.addAttribute("subGovernances", adminSubGovernanceFacade.getList());
        return "admin/subGovernance/subgov-list";
    }

    @GetMapping("/subGovernances/delete")
    public String deleteSubGovernance(@RequestParam(value = "id") long id, Model model){
        model.addAttribute("subGovernances", adminSubGovernanceFacade.delete(id));
        return "admin/subGovernance/subgov-list";
    }

    @PostMapping("/subGovernances/edit")
    public String editSubGovernance(@ModelAttribute SubGovernanceDTO subGovernance, Model model) throws NotFoundException {
        model.addAttribute("subGovernances", adminSubGovernanceFacade.edit(subGovernance));
        return "admin/subGovernance/subgov-list";
    }

    @GetMapping("/subGovernances/view")
    public String viewSubGovernance(@RequestParam(value = "id") long id,Model model){
        model.addAttribute("subGov", adminSubGovernanceFacade.getById(id));
        return "admin/subGovernance/subgov-view";
    }

    @GetMapping("/contests/list")
    public String contestsList(Model model){
        model.addAttribute("contests", adminContestFacade.getList());
        return "admin/contest/contest-list";
    }

    @GetMapping("/contests/openAdd")
    public String openContestAddPage(Model model){
        model.addAttribute("subGovernances", adminSubGovernanceFacade.getList());
        return "admin/contest/contest-create";
    }

    @GetMapping("/contests/openEdit")
    public String openContestEditPage(@RequestParam(value = "id") long id, Model model, Principal principal){
        ContestDTO contest = adminContestFacade.getById(id);
        if (contest == null){
            model.addAttribute("errorMessage", "You cannot edit current user.");
            return "error";
        }
        model.addAttribute("contest", contest);
        model.addAttribute("subGovernances", adminSubGovernanceFacade.getList());
        return "admin/contest/contest-edit";
    }

    @PostMapping("/contests/add")
    public String createContest(@ModelAttribute ContestDTO contest, Model model){
        adminContestFacade.create(contest);
        model.addAttribute("contests", adminContestFacade.getList());
        return "admin/contest/contest-list";
    }

    @GetMapping("/contests/delete")
    public String deleteContest(@RequestParam(value = "id") long id, Model model){
        model.addAttribute("contests", adminContestFacade.delete(id));
        return "admin/contest/contest-list";
    }

    @PostMapping("/contests/edit")
    public String editContest(@ModelAttribute ContestDTO contest, Model model) throws NotFoundException {
        model.addAttribute("contests", adminContestFacade.edit(contest));
        return "admin/contest/contest-list";
    }

    @GetMapping("/submissions/view")
    public String viewSubmission(@RequestParam(value = "id") long id, Model model){
        model.addAttribute("submission", adminSubmissionFacade.getById(id));
        return "admin/submission/submission-view";
    }

    @GetMapping("/submissions/openAdd")
    public String openSubmissionAddPage(Model model){
        model.addAttribute("contests", adminContestFacade.getList());
        model.addAttribute("authors", adminAuthorFacade.getList());
        return "admin/submission/submission-create";
    }

    @GetMapping("/submissions/openEdit")
    public String openSubmissionEditPage(@RequestParam(value = "id") long id, Model model, Principal principal){
        SubmissionDTO submissionDTO = adminSubmissionFacade.getById(id);
        if (submissionDTO == null){
            model.addAttribute("errorMessage", "You cannot edit submission.");
            return "error";
        }
        model.addAttribute("submission", submissionDTO);
        model.addAttribute("contests", adminContestFacade.getList());
        model.addAttribute("authors", adminAuthorFacade.getList());
        return "admin/submission/submission-edit";
    }

    @GetMapping("/submissions/list")
    public String submissionList(Model model){
        model.addAttribute("submissions", adminSubmissionFacade.getList());
        return "admin/submission/submission-list";
    }

    @GetMapping("/submissions/delete")
    public String deleteSubmission(@RequestParam(value = "id") long id, Model model){
        adminSubmissionFacade.delete(id);
        model.addAttribute("submissions", adminSubmissionFacade.getList());
        return "admin/submission/submission-list";
    }

    @PostMapping("/submissions/add")
    public String addSubmission(@ModelAttribute SubmissionDTO submissionDTO, Model model){
        adminSubmissionFacade.create(submissionDTO);
        model.addAttribute("submissions", adminSubmissionFacade.getList());
        return "admin/submission/submission-list";
    }

    @PostMapping("/submissions/edit")
    public String editSubmission(@ModelAttribute SubmissionDTO submissionDTO, Model model) throws NotFoundException {
        adminSubmissionFacade.edit(submissionDTO);
        model.addAttribute("submissions", adminSubmissionFacade.getList());
        return "admin/submission/submission-list";
    }

    @PostMapping("/submissions/link/add")
    public String addSubmissionLink(@ModelAttribute LinkDBDTO linkDBDTO, Model model) throws NotFoundException {
        adminSubmissionFacade.addSubmissionLink(linkDBDTO);
        model.addAttribute("submission", adminSubmissionFacade.getById(linkDBDTO.getSubmissionId()));
        return "admin/submission/submission-view";
    }

    @GetMapping("/submissions/link/delete")
    @ResponseBody
    public void deleteSubmissionLink(@RequestParam(value = "submissionId") long submissionId, @RequestParam(value = "linkId") long linkId, Model model){
        adminSubmissionFacade.deleteSubmissionLink(submissionId, linkId);
    }

    @GetMapping("/contests/view")
    public String viewContest(@RequestParam(value = "id") long id,Model model){
        model.addAttribute("contest", adminContestFacade.getById(id));
        return "admin/contest/contest-view";
    }


    @GetMapping("/authors/list")
    public String authorList(Model model){
        model.addAttribute("authors", adminAuthorFacade.getList());
        return "admin/author/author-list";
    }
    @GetMapping("/authors/openAdd")
    public String authorOpenAddPage(Model model){
        return "admin/author/author-create";
    }
    @GetMapping("/authors/openEdit")
    public String authorOpenEditPage(@RequestParam(value = "id") long id, Model model){
        model.addAttribute("author", adminAuthorFacade.getById(id));
        return "admin/author/author-edit";
    }

    @PostMapping("/authors/add")
    public String addAuthor(@ModelAttribute AuthorDTO author, Model model){
        adminAuthorFacade.create(author);
        model.addAttribute("authors", adminAuthorFacade.getList());
        return "admin/author/author-list";
    }

    @PostMapping("/authors/edit")
    public String editAuthor(@ModelAttribute AuthorDTO author, Model model){
        model.addAttribute("authors", adminAuthorFacade.edit(author));
        return "admin/author/author-list";
    }

    @GetMapping("/authors/view")
    public String authorOpenViewPage(@RequestParam(value = "id") long id, Model model){
        model.addAttribute("author", adminAuthorFacade.getById(id));
        return "admin/author/author-view";
    }

    @Autowired
    public void setAdminManagerPageFacade(AdminManagerPageFacade adminManagerPageFacade) {
        this.adminManagerPageFacade = adminManagerPageFacade;
    }

    @Autowired
    public void setAdminSubGovernanceFacade(AdminSubGovernanceFacade adminSubGovernanceFacade) {
        this.adminSubGovernanceFacade = adminSubGovernanceFacade;
    }

    @Autowired
    public void setAdminContestFacade(AdminContestFacade adminContestFacade) {
        this.adminContestFacade = adminContestFacade;
    }

    @Autowired
    public void setAdminSubmissionFacade(AdminSubmissionFacade adminSubmissionFacade) {
        this.adminSubmissionFacade = adminSubmissionFacade;
    }

    @Autowired
    public void setAdminAuthorFacade(AdminAuthorFacade adminAuthorFacade) {
        this.adminAuthorFacade = adminAuthorFacade;
    }
}
