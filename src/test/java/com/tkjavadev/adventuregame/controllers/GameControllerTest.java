package com.tkjavadev.adventuregame.controllers;

//@RunWith(SpringRunner.class)
//@WebFluxTest(GameController.class)
public class GameControllerTest {


//    @Autowired
//    private WebTestClient webTestClient;
//
//    @MockBean
//    private GameService gameService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void play() throws Exception {

//        when(gameService.getDescription()).thenReturn(Mono.just("Description"));
//
//        webTestClient.get().uri("/" + GameMappings.PLAY)
//                .exchange().expectStatus().isOk();

//        mockMvc.perform(get("/" + GameMappings.PLAY))
//                .andExpect(status().isOk())
//                .andExpect(view().name(ViewNames.PLAY));
//
//        when(gameService.isGameOver()).thenReturn(true);
//
//        mockMvc.perform(get("/" + GameMappings.PLAY))
//                .andExpect(status().isOk())
//                .andExpect(view().name(ViewNames.GAME_OVER));
//    }

//    @Test
//    public void processMessage() throws Exception {
//        mockMvc.perform(post("/" + GameMappings.CHANGE).param("direction", "Q"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name(GameMappings.REDIRECT_PLAY));
//    }
//
//    @Test
//    public void addItemToInventory() throws Exception {
//        mockMvc.perform(post("/" + GameMappings.ADD).param("item", "GOLD"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name(GameMappings.REDIRECT_PLAY));
//    }
//
//    @Test
//    public void home() throws Exception {
//        mockMvc.perform(get(GameMappings.HOME))
//                .andExpect(status().isOk())
//                .andExpect(view().name(ViewNames.HOME));
//    }
//
//    @Test
//    public void restart() throws Exception {
//        mockMvc.perform(get("/" + GameMappings.RESTART))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name(GameMappings.REDIRECT_PLAY));
//    }
//
//    @Test
//    public void exit() throws Exception {
//        mockMvc.perform(get("/" + GameMappings.EXIT))
//                .andExpect(status().isOk())
//                .andExpect(view().name(ViewNames.GAME_OVER));
//    }

//    @Test
//    public void changeDirectionNotFound() throws Exception {
//
//        when(gameService.changeDirection(anyString())).thenThrow(NotFoundException.class);
//
//        mockMvc.perform(post("/" + GameMappings.CHANGE).param("direction", "Q"))
//                .andExpect(status().isNotFound())
//                .andExpect(view().name("404error"));
//    }

}