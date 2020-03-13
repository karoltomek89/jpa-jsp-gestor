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
            <a class="nav-link" id="v-pills-messages-tab" href="${pageContext.request.contextPath}/getstudentlist"
               role="tab"
               aria-controls="v-pills-messages" aria-selected="false">Wiadomości</a>
            <a class="nav-link" id="v-pills-logout-tab" href="${pageContext.request.contextPath}/logout" role="tab"
               aria-controls="v-pills-logout" aria-selected="false">Wyloguj</a>

        </div>
    </div>
    <div class="col-11">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade" id="v-pills-notes" role="tabpanel"
                 aria-labelledby="v-pills-notes-tab">
                Tutaj będzie można dodać uczniowi uwagi.
            </div>
            <div class="tab-pane fade" id="v-pills-homework" role="tabpanel"
                 aria-labelledby="v-pills-homework-tab">
                Tutaj będzie można dodać uczniom prace domowe.
            </div>
            <div class="tab-pane fade" id="v-pills-students" role="tabpanel"
                 aria-labelledby="v-pills-students-tab">
                Tutaj będzie można przeglądać dane o uczniach.
            </div>
        </div>
    </div>
</div>