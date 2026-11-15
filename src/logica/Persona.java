package logica;

public abstract class Persona implements Mostrable {
    protected String dni;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String telefono;

    protected Persona(String dni, String nombre, String apellido, String email, String telefono) {
        this.dni = dni; 
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters and Setters
    protected String getDni() {
        return dni;
    }

    protected void setDni(String dni) {
        this.dni = dni;
    }

    protected String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) {
        this.nombre = nombre;
    }

    protected String getApellido() {
        return apellido;
    }

    protected void setApellido(String apellido) {
        this.apellido = apellido;
    }

    protected String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected String getTelefono() {
        return telefono;
    }

    protected void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override 
    public String toString(){
        return "\nPersona: " +
                "\nDNI: " + dni +
                "\nNombre:" + nombre +
                "\nApellido: " + apellido +
                "\nEmail: " + email +
                "\nTelefono: " + telefono ;
    }
    
    
    public abstract void mostrarInformacion();

}
