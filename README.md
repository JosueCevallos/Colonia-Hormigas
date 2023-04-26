# Colonia-Hormigas
<h2>Simulación de una colonia de hormigas</h2>

<p>
<ul>
  <li>La colonia cuenta contres túneles, uno de entrada y dos de salida. 
  Por cada uno de ellos solo puede pasar una hormiga a la vez y tardan 0.1 segundos. 
  <li>Para evitar aglomeraciones las hormigas deberán ser creadas (instanciadas) de forma escalonada
  en intervalos de 0'8 y 3'5 segundos. 
  <li>La colonia cuenta con cuatro zonas diferenciadas: ALMACÉN DE COMIDA, ZONA DE INSTRUCCIÓN, ZONA
  PARA COMER, ZONA DE DESCANSO Y REFUGIO.
  <li>El sistema creará 10.000 hormigas de las cuales 6.000 serán obreras, 2.000 soldado y 2.000 crias.
  <li>Por cada 3 obreras creadas se crea 1 soldado y 1 cria.
  <li>Cada hormiga tendrá un comportamiento diferente.
</ul>
</p>

<h4>HORMIGAS OBRERAS</h4>
<p>
Las hormigas deberán ser modeladas como y se identificarán como “HOXXXX”, donde X es un número (id) único 
Ejemplo: HO0001, HO0023, HO1234, etc.
Hormiga obrera con identificador impar, repetirá el siguiente comportamiento:
<ul>
  <li>Acude a la zona exterior de la colonia -coge cinco elementos de comida - lleva al 
interior de la colonia (tarda 4 segundos). 
  <li>Los deposita en el ALMACÉN DE COMIDA (tarda entre 2 y 4 segundos). Al ALMACÉN 
  DE COMIDA sólo pueden acceder simultáneamente 10 hormigas.
</ul>
</p>
