package com.vtb.idrteam.taskmanager;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.dtos.RequestNewProjectDto;
import com.vtb.idrteam.taskmanager.entities.dtos.RequestNewTaskDto;
import com.vtb.idrteam.taskmanager.entities.dtos.UserDto;
import com.vtb.idrteam.taskmanager.exceptions.UserCreationException;
import com.vtb.idrteam.taskmanager.repositories.NotificationRepository;
import com.vtb.idrteam.taskmanager.repositories.ProjectRepository;
import com.vtb.idrteam.taskmanager.repositories.TaskRepository;
import com.vtb.idrteam.taskmanager.repositories.UserRepository;
import com.vtb.idrteam.taskmanager.services.NotificationService;
import com.vtb.idrteam.taskmanager.services.ProjectService;
import com.vtb.idrteam.taskmanager.services.TaskService;
import com.vtb.idrteam.taskmanager.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class Tests {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Тест проверяет работу UserService
     * на поиск по Id
     */
    @Test
    public void userFindById() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        given(this.userRepository.findById(any()))
                .willReturn(Optional.of(user1));

        User user = userService.findById(1L).orElseThrow(Exception::new);
        assertEquals(1, user.getId());

    }

    /**
     * Тест проверяет работу UserService
     * на поиск по username
     */
    @Test
    public void userFindByUsername() throws Exception {
        User user1 = new User();
        user1.setUsername("qwe");
        given(this.userRepository.findByUsername(any()))
                .willReturn(Optional.of(user1));

        User user = userService.findByUsername("qwe").orElseThrow(Exception::new);
        assertEquals("qwe",user.getUsername());
    }

    /**
     * Тест проверяет работу UserService
     * на обновление user
     */
    @Test
    public void userSaveOrUpdate() throws Exception {
        User user1 = new User();
        user1.setUsername("qwe");
        given(this.userRepository.findByUsername(any()))
                .willReturn(Optional.of(user1));

        User user = userService.findByUsername("qwe").orElseThrow(Exception::new);
        assertEquals("qwe",user.getUsername());

        user1.setUsername("another");
        given(this.userRepository.save(any()))
                .willReturn(user1);
        user = userService.saveOrUpdate(user1);
        assertEquals("another",user.getUsername());

    }

    /**
     * Тест проверяет работу UserService
     * на создание user по  Dto
     * с отсутствием 2го пароля
     * - ожидается ошибка UserCreationException
     * Enter both passwords
     */
    @Test
    public void userDtoCreateWithoutConfirmPass(){
        User user1 = new User();
        user1.setUsername("qwe");
        given(this.userRepository.save(any()))
                .willReturn(user1);

        UserDto userDto = new UserDto();
        userDto.setPassword("123");
        userDto.setSurname("124");
        userDto.setEmail("aasdas");
        Assertions.assertThrows(UserCreationException.class, ()->{
            User user = userService.createUser(userDto);
        });

    }

    /**
     * Тест проверяет работу UserService
     * на создание user по  Dto
     * с несовпадающими паролями
     * - ожидается ошибка UserCreationException
     * Passwords doesnt match
     */
    @Test
    public void userCreateInccorectPasswords(){
        User user1 = new User();
        user1.setUsername("qwe");
        given(this.userRepository.save(any()))
                .willReturn(user1);

        UserDto userDto = new UserDto();
        userDto.setPassword("123");
        userDto.setPasswordConfirm("124");
        userDto.setSurname("124");
        userDto.setEmail("aasdas");
        Assertions.assertThrows(UserCreationException.class, ()->{
            User user = userService.createUser(userDto);
        });
    }

    /**
     * Тест проверяет работу UserService
     * на создание user по  Dto
     * без username
     * - ожидается ошибка UserCreationException
     * Enter username
     */
    @Test
    @Disabled()
    public void userCreateWithoutUsername(){
        User user1 = new User();
        user1.setUsername("qwe");
        given(this.userRepository.save(any()))
                .willReturn(user1);

        UserDto userDto = new UserDto();
        userDto.setPassword("123");
        userDto.setPasswordConfirm("123");
        Assertions.assertThrows(UserCreationException.class, ()->{
            User user = userService.createUser(userDto);
        });

    }

    /**
     * Тест проверяет работу UserService
     * на создание user по  Dto
     * с несовпадающими паролями
     * - ожидается ошибка UserCreationException
     * Enter email
     */
    @Test
    @Disabled
    public void userCreateWithoutEmail(){
        User user1 = new User();
        user1.setUsername("qwe");
        given(this.userRepository.save(any()))
                .willReturn(user1);

        UserDto userDto = new UserDto();
        userDto.setPassword("123");
        userDto.setPasswordConfirm("123");
        userDto.setSurname("123");
        Assertions.assertThrows(UserCreationException.class, ()->{
            User user = userService.createUser(userDto);
        });

    }


    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;


    /**
     * Тест проверяет taskCreate
     * на поиск по Id
     */
    @Test
    public void taskFindById() throws Exception {
        Task task = new Task();
        task.setId(1L);
        given(this.taskRepository.findById(any()))
                .willReturn(java.util.Optional.of(task));
        Task task1 = taskService.findById(1L).orElseThrow(Exception::new);
        assertEquals(1,task1.getId());
    }


    @MockBean
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    /**
     * Тест проверяет taskCreate
     * на создание task из ДТО
     */
    @Test
    public void taskCreateFromDTO(){
        Task task = new Task();
        task.setId(1L);
        task.setName("asd");
        task.setArchived(false);
        task.setDescription("asdfgh");
        task.setState(Task.State.CREATED);
        RequestNewTaskDto taskDto = new RequestNewTaskDto();
        taskDto.setName("asd");
        taskDto.setState(Task.State.CREATED);
        given(this.taskRepository.save(any()))
                .willReturn(task);
        given(this.taskRepository.findById(any()))
                .willReturn(java.util.Optional.of(task));
        given(this.userRepository.findByUsername(any()))
                .willReturn(Optional.of(new User()));

        Project project = new Project();
        project.setId(1L);
        given(this.projectRepository.findById(any()))
                .willReturn(java.util.Optional.of(project));
        Task task2 = taskService.createNewTask(1L,taskDto,"ASDFF");
        assertEquals("asd", task2.getName());
        assertEquals("asdfgh", task2.getDescription());
        assertEquals(Task.State.CREATED, task2.getState());
    }

    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    /**
     * @throws Exception
     * Тест проверяет работу ProjectService
     * на поиск по id
     */
    @Test
    public void projectFindById() throws Exception {

        Project project = new Project();
        project.setId(1L);
        given(this.projectRepository.findById(any()))
                .willReturn(java.util.Optional.of(project));
        Project project1 = projectService.findById(1L).orElseThrow(Exception::new);
        assertEquals(1,project1.getId());
    }

    /**
     * Тест проверяет работу ProjectService
     * на создание проекта
     */
    @Test
    public void projectCreate() {

        Project project = new Project();
        project.setId(1L);
        project.setDescription("desc");
        RequestNewProjectDto  projectDto = new RequestNewProjectDto();
        projectDto.setDescription("desc");
        projectDto.setName("NAME");
        given(this.projectRepository.save(any()))
                .willReturn(project);
        given(this.userRepository.findByUsername(any()))
                .willReturn(Optional.of(new User()));
        Project project1 = projectService.createNewProject(projectDto, "qweqwe");
        assertEquals(1,project1.getId());
        assertEquals("desc",project1.getDescription());
    }


}
