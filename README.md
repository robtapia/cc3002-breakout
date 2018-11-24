Breakout
======
  El presente repositorio contiene el controlador lógico de un juego llamado "Breakout" el cual fue implementado como tarea 2 del ramo CC-3201 de la Universidad de Chile.
  El juego se basa en niveles que contienen ladrillos, una vez todos los ladrillos de un nivel han sido destruidos, se pasa al siguiente nivel. Para la implementación de la tarea, los elementos que controlan la logica del juego son las siguientes:
    
    
    1. Fachada (HomeworkTwoFacade)
    2. Game
    3. Level
    4. Brick
    
  
  Fachada
  ------
  Esta implementada mediante la clase HomeworkTwoFacade, se basa en un patrón de diseño llamada "Facade" y es basicamente la creacion de una interfaz diferente para la clase Game, esto debido a que todos sus metodos son finalmente llamadas a metodos de dicha clase. La clase HomeworkTwoFacade sera la base para una interfaz grafica que sera implementada como siguiente tarea del curso. Cuenta con metodos para conocer distintos elementos del estado actual del juego, como la cantidad de bolas restantes, el puntaje obtenido, el nivel siguiente, si el nivel actual es jugable, y tambien tiene metodos para alterar el juego, como agregar niveles, cambiar el nivel actual, etc.
  
  Game
  ------
  Esta implementado mediante la clase Game, es lo que controla en la practica la lógica del juego, implementa dos patrones de diseño distintos, "Observer" y "Visitor", respecto al primero, observa tanto levels como ladrillos, para saber cuando cambiar de nivel y de puntaje respectivamente, sobre el segundo patron, es usado para contar la cantidad de ladrillos destruibles en un level. Contiene la base logica para todos los metodos implementados en HomeworkTwoFacade.
  
  Level
  ------
  El comportamiento basico necesario de un level se encuentra en la interfaz Level, la que extiende las interfaces ObservableByGame y Visitor, las que garantizan que cualquier objeto Level puede ser observado por un game y ademas puede ser visitado por el Visitor implementado en la clase Game para contar ladrillos. La implementacion de la interfaz se basa en el patron de diseño "Null", esto ya que los levels operan como una lista enlazada y se necesitan ciertos comportamientos basicos de los niveles "nulos" o no-jugables. 
  
  Brick
  ------
  Se basa en una interfaz "Brick" que extiende las interfaces ObservableByGame, ObservableByLevel y Visitor, ya que es necesario que cada bric sepa como notificar a un game o a un level de que ha sucedido un cambio y como reaccionar ante el, y debe tambien poder aceptar al visitor para contar ladrillos. La interfaz Brick es implementada por 3 clases, WoodenBrick, GlassBrick y MetalBrick, la unica diferencia entre estas clases son las recompensas que sus objetos dan al ser destruidos y la cantidad de golpes que esto requiere. 
  
  
  Finalmente, las partes mas interesantes de la implementacion son los patrones Observer y Visitor, los cuales seran explicados brevemente a continuación.
  
  Observer
  ------
  Se tienen dos Observers y dos Observables. Los Observers son Game (que observa Levels y Bricks) y Level (que observa solo Bricks).
  El comportamiento cuando se golpea un brick consiste en revisar si dicho brick esta roto luego de ser golpeado, en caso de que esto haya sido asi, se notifica a sus observers, quienes verifican que quien envio la notificacion sea efectivamente una instancia de ObservableByGame/Level segun corresponda, una vez esto ocurre, se produce un double-dispatch para poder confirmar que fue lo que mando la notificacion (es decir, que tipo de ladrillo), en base a esto, Game sumara los puntos correspondientes y Level sacara al brick del ArrayList necesario, ademas de revisar si es que es necesario terminar el level actual, en cuyo caso notificara tambien a su propio Observer, es decir, a Game.
  Por otro lado, cuando se termina un level y este avisa a Game, mediante el mismo mecanismo anteriormente descrito, ante lo cual Game cambia su level actual por el siguiente en la lista.
  Visitor
  ------
  La implementacion de Visitor consiste en la clase BrickCounter, la cual puede visitar tipos que implementen la interfaz visitable, en este caso particular, Visitable es implementado por bricks y por levels. Cuando un level es visitado, le dice a sus bricks que "acepten" al visitor, y cuando un brick es visitado, si es que no esta destruido, suma 1 al contador del visitor. Finalmente se usa getCounter para recibir la cantidad de ladrillos no destruidos en un nivel.
  
  
