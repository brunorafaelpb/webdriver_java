package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class cadastrarNovoUsuarioTest {
    private  WebDriver navegador;

    @Before
    public void setUp(){
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Bruno Rafael\\Documents\\Drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
    }


    @Test
    public void testCadastrarNovoUsuario(){
        //Navegando na página
        navegador.get("http://municipioalerta.abbratech.com.br/wp-admin");

        //Identificar e digitar no campo de ID "user_login, o texto "goku"
        WebElement formularioLogin = navegador.findElement(By.id("loginform"));
        formularioLogin.findElement(By.id("user_login")).sendKeys("goku");

        //Digitar o no campo "user_pass" o texto "@bbra@dmn2O18"
        formularioLogin.findElement(By.id("user_pass")).sendKeys("@bbra@dmn2O18");

        //Clicar elemento com id "wp-submit"
        formularioLogin.findElement(By.id("wp-submit")).click();

        //Validar que dentro do elemento com id "wpadminbar", está o texto "Olá, goku"
        //WebElement validacao = navegador.findElement(By.className("display-name"));
        //String textoValidacao = validacao.getText();
        //assertEquals("goku",textoValidacao);



        //Clicar no elemento com ID "menu-users"
        WebElement acessarUsuario = navegador.findElement(By.id("adminmenu"));
        acessarUsuario.findElement(By.id("menu-users")).click();

        //Clicar no elemento com text "Adicionar novo"
        navegador.findElement(By.linkText("Adicionar novo")).click();

        //Informar campos obrigatórios com ID "user_login" e ID "email"
        navegador.findElement(By.id("user_login")).sendKeys("teste");
        navegador.findElement(By.id("email")).sendKeys("teste@teste.com");

        //Definir a função com o text "Assinante" para o novo usuário através do combobox com ID "role"
        WebElement campoFuncao = navegador.findElement(By.id("role"));
        new Select(campoFuncao).selectByVisibleText("Assinante");

        //Clicar no botão com ID "createusersub"
        navegador.findElement(By.id("createusersub")).click();

        //Verificar se o novo usuário foi cadastrado com sucesso buscando atraves do text "teste"
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement tabelaUsuarios = navegador.findElement(By.id("the-list"));
        WebElement validacao = tabelaUsuarios.findElement(By.linkText("teste"));
        assertEquals("teste",validacao.getText());

    }

    @After
    public void tearDown(){
        //Fechar o navegador
        navegador.quit();
    }

}
