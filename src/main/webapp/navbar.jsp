<nav class="navbar navbar-expand-xl navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Panel nawigacyjny</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="start" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    START
                </a>

                <div class="dropdown-menu" aria-labelledby="start">
                    <a class="dropdown-item" data-toggle="modal" data-target="#loginModal">Logowanie</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" data-toggle="modal" data-target="#registrationModal">Rejestracja</a>
                </div>
            </li>

            <!-- Modal -->
            <div class="modal fade" id="registrationModal" tabindex="-1" role="dialog"
                 aria-labelledby="registrationModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="registrationModalLabel">Panel rejestracji</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <form id="registration" method="post" action="student">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="Name">Imię</label>
                                        <input type="text" class="form-control" name="name" placeholder="Name">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="Surname">Nazwisko</label>
                                        <input type="text" class="form-control" name="surname" placeholder="Surname">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="Email">Email</label>
                                    <input type="text" class="form-control" name="email" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <label for="Password">Hasło</label>
                                    <input type="text" class="form-control" name="password" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <label for="Type" id="type" >Typ konta</label>
                                    <select name="type" class="custom-select">
                                        <option selected>Wybierz typ konta</option>
                                        <option value="student">student</option>
                                        <option value="teacher">Nauczyciel</option>
                                        <option value="director">Dyrektor</option>
                                        <option value="parent">Rodzic</option>
                                    </select>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">

                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj</button>
                            <input type="submit" form="registration" class="btn btn-primary">Zarejestruj</input>

                        </div>
                    </div>
                </div>
            </div>





            <!-- Modal -->
            <div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
                 aria-labelledby="loginModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="loginModalLabel">Panel logowania</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">


                            <form id="login" method="post" action="login">
                                <div class="form-group row">
                                    <label for="inputEmail" class="col-4 col-form-label">Twój adres e-mail</label>
                                    <div class="col-8">
                                        <input type="text" class="form-control" id="inputEmail" name="inputEmail" placeholder="e-mail">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-4 col-form-label">Twoje hasło</label>
                                    <div class="col-8">
                                        <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password">
                                    </div>
                                </div>
                            </form>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj</button>
                            <input type="submit" form="login" class="btn btn-primary">Zaloguj</input>
                        </div>
                    </div>
                </div>
            </div>




        </ul>
    </div>


</nav>
