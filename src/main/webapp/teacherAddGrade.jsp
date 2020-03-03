<div class="row">
    <div class="col-1">
        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link" id="v-pills-teacher-tab" href="${pageContext.request.contextPath}/teacher" role="tab"
               aria-controls="v-pills-teacher" aria-selected="false">Info</a>
            <a class="nav-link active" id="v-pills-grades-tab" href="${pageContext.request.contextPath}/getsubjectlist"
               role="tab" aria-controls="v-pills-grades" aria-selected="true">Dodaj ocenę</a>
            <a class="nav-link" id="v-pills-notes-tab" data-toggle="pill" href="#v-pills-notes" role="tab"
               aria-controls="v-pills-notes" aria-selected="false">Uwagi</a>
            <a class="nav-link" id="v-pills-homework-tab" data-toggle="pill" href="#v-pills-homework" role="tab"
               aria-controls="v-pills-homework" aria-selected="false">Prace domowe</a>
            <a class="nav-link" id="v-pills-students-tab" data-toggle="pill" href="#v-pills-students" role="tab"
               aria-controls="v-pills-students" aria-selected="false">Uczniowie</a>
            <a class="nav-link" id="v-pills-logout-tab" href="${pageContext.request.contextPath}/logout" role="tab"
               aria-controls="v-pills-logout" aria-selected="false">Wyloguj</a>

        </div>
    </div>
    <div class="col-11">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade show active" id="v-pills-grades" role="tabpanel"
                 aria-labelledby="v-pills-grades-tab">
                <div class="modal-body">
                    <form id="addGrade" method="post" action="addgrade">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>Id ucznia</label>
                                <input type="text" class="form-control" name="studentId" placeholder="StudentId">
                            </div>

                            <div class="form-group col-md-6">
                                <label>Wybierz przedmiot</label>
                                <select name="subjectId" class="custom-select">

                                    <c:forEach var="item" items="${subjectList}">
                                        <option value="${item.subjectId}">${item.name}</option>
                                    </c:forEach>

                                </select>
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="Grade" id="grade">Ocena</label>
                            <select name="grade" class="custom-select">
                                <option selected>Wybierz ocenę</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj</button>
                    <button type="submit" form="addGrade" class="btn btn-primary">Dodaj ocenę</button>

                </div>
            </div>
        </div>
    </div>
</div>