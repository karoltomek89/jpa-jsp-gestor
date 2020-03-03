<div class="row">
    <div class="col-1">
        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link" id="v-pills-student-tab" href="${pageContext.request.contextPath}/student" role="tab"
               aria-controls="v-pills-student" aria-selected="false">Info</a>
            <a class="nav-link" id="v-pills-grades-tab" href="${pageContext.request.contextPath}/showgrades" role="tab"
               aria-controls="v-pills-grades" aria-selected="true">Oceny</a>
            <a class="nav-link" id="v-pills-notes-tab" data-toggle="pill" href="#v-pills-notes" role="tab"
               aria-controls="v-pills-notes" aria-selected="false">Uwagi</a>
            <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab"
               aria-controls="v-pills-messages" aria-selected="false">Wiadomości</a>
            <a class="nav-link" id="v-pills-homework-tab" data-toggle="pill" href="#v-pills-homework" role="tab"
               aria-controls="v-pills-homework" aria-selected="false">Prace domowe</a>
            <a class="nav-link" id="v-pills-logout-tab" href="${pageContext.request.contextPath}/logout" role="tab"
               aria-controls="v-pills-logout" aria-selected="false">Wyloguj</a>
        </div>
    </div>

    <div class="col-11">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade" id="v-pills-grades" role="tabpanel" aria-labelledby="v-pills-grades-tab">
                Tutaj będą prezentowane uzyskane przez ucznia oceny
            </div>
            <div class="tab-pane fade" id="v-pills-notes" role="tabpanel" aria-labelledby="v-pills-notes-tab">
                Tutaj będą prezetowane uwagi od nauczycieli
            </div>
            <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">
                Tutaj będą prezentowane wiadomości
            </div>
            <div class="tab-pane fade" id="v-pills-homework" role="tabpanel" aria-labelledby="v-pills-homework-tab">
                Tutaj będą prezentowane zadane prace domowe
            </div>
        </div>
    </div>
</div>