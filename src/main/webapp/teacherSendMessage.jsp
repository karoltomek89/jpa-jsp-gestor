<div class="row">
    <div class="col-1">
        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link" id="v-pills-user-tab" href="${pageContext.request.contextPath}/teacher" role="tab"
               aria-controls="v-pills-user" aria-selected="false">Info</a>
            <a class="nav-link" id="v-pills-grades-tab" href="${pageContext.request.contextPath}/getgrouplist"
               role="tab" aria-controls="v-pills-grades" aria-selected="true">Dodaj ocenę</a>
            <a class="nav-link" id="v-pills-notes-tab" data-toggle="pill" href="#v-pills-notes" role="tab"
               aria-controls="v-pills-notes" aria-selected="false">Uwagi</a>
            <a class="nav-link" id="v-pills-homework-tab" data-toggle="pill" href="#v-pills-homework" role="tab"
               aria-controls="v-pills-homework" aria-selected="false">Prace domowe</a>
            <a class="nav-link" id="v-pills-students-tab" data-toggle="pill" href="#v-pills-students" role="tab"
               aria-controls="v-pills-students" aria-selected="false">Uczniowie</a>
            <a class="nav-link active" id="v-pills-messages-tab"
               href="${pageContext.request.contextPath}/getstudentlist"
               role="tab"
               aria-controls="v-pills-messages" aria-selected="false">Wiadomości</a>
            <a class="nav-link" id="v-pills-logout-tab" href="${pageContext.request.contextPath}/logout" role="tab"
               aria-controls="v-pills-logout" aria-selected="false">Wyloguj</a>

        </div>
    </div>
    <div class="col-11">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade show active" id="v-pills-messages" role="tabpanel"
                 aria-labelledby="v-pills-messages-tab">
                <div class="modal-body">
                    <form id="sendMessage" method="post" action="messages">
                        <div class="form-row">

                            <div class="form-group col-md-6">
                                <label>Wybierz klasę</label>
                                <select name="subjectId" class="custom-select">
                                    <c:forEach var="item" items="${classesList}">
                                        <option value="${item.classId}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>Uczeń</label>
                                <select name="studentId" class="custom-select">
                                    <c:forEach var="item" items="${studentsList}">
                                        <option value="${item.studentId}">${item.name} ${item.surname} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>Temat</label>
                                <input type="text" class="form-control" name="topic">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6 ">
                                <label>Tekst</label>
                                <textarea class="form-control" rows="6" placeholder="Wpisz tekst wiadomości"
                                          name="text"></textarea>
                            </div>

                        </div>

                    </form>
                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj</button>
                    <button type="submit" form="sendMessage" class="btn btn-primary">Wyślij</button>
                </div>
            </div>
        </div>
    </div>
</div>