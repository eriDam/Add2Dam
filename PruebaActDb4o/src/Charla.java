
public class Charla {
  private String titulo;
  private float duracion;
  private Ponente ponente;
  
  //constructores
  /**
   * Crea un objeto de la clase Charla
   * @param titulo el titulo que llevar� la charla
   * @param duracion la duraci�n que tendr� la charla
   * @param ponente  el ponente que impartir� la charla
   */
  public Charla(String titulo, float duracion, Ponente ponente) {
    this.titulo = titulo;
    this.ponente = ponente;
    this.duracion=duracion;
  }
  /**
   * Crea un objeto de la clase Charla
   * @param titulo el titulo que llevar� la charla
   * @param duracion la duracion que tendr� la charla
   */
  public Charla(String titulo,float duracion){
      this(titulo,duracion,null);
  }
  /**
   * Permite obtener el ponente que realizar� la charla
   * @return el ponente que realizar� la charla
   */
  public Ponente getPonente() {
    return ponente;
  }
/**
 * Permite establecer el ponente que realizar� la charla
 * @param ponente el ponente que realizar� la charla
 */
  public void setPonente(Ponente ponente) {
    this.ponente = ponente;
  }
  /**
   * Permite establecer el titulo que tendr� la charla
   * @param titulo el titulo que tendr� la charla
   */
  public void setTitulo(String titulo){
      this.titulo=titulo;
  }
  /**
   * Permite obtener el titulo que tendr� la charla
   * @return el titulo que tendr� la charla
   */
  public String getTitulo() {
    return titulo;
  }
  /**
   * Permite obtener la duraci�n que tendr� la charla
   * @return la duraci�n que tendr� la charla
   */
  public float getDuracion() {
    return duracion;
  }
  /**
   * Permite establecer la duracion que tendr� la charla
   * @param duracion la duracion que tendr� la charla
   */
  public void setDuracion(float duracion) {
    this.duracion = duracion;
  }
  
  /**
   * Devuelve todos los atributos del objeto representados en un String
   * @return los atributos del objeto en forma de String
   */
  @Override
  public String toString() {
    return "Charla: " + titulo + ", " + duracion + " horas.  PONENTE: " +ponente ;
  }  
}
