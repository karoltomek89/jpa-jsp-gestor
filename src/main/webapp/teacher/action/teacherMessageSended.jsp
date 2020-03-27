<div class="row">
    <div class="col-1">
        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link" id="v-pills-user-tab" href="${pageContext.request.contextPath}/teacher" role="tab"
               aria-controls="v-pills-user" aria-selected="false">Info</a>
            <a class="nav-link" id="v-pills-grades-tab" href="${pageContext.request.contextPath}/getgrouplistforgrades"
               role="tab" aria-controls="v-pills-grades" aria-selected="true">Dodaj ocenę</a>
            <a class="nav-link" id="v-pills-notes-tab" data-toggle="pill" href="#v-pills-notes" role="tab"
               aria-controls="v-pills-notes" aria-selected="false">Uwagi</a>
            <a class="nav-link" id="v-pills-homework-tab" data-toggle="pill" href="#v-pills-homework" role="tab"
               aria-controls="v-pills-homework" aria-selected="false">Prace domowe</a>
            <a class="nav-link" id="v-pills-students-tab" data-toggle="pill" href="#v-pills-students" role="tab"
               aria-controls="v-pills-students" aria-selected="false">Uczniowie</a>
            <a class="nav-link active" id="v-pills-messages-tab"
               href="${pageContext.request.contextPath}/getgrouplistformessages"
               role="tab"
               aria-controls="v-pills-messages" aria-selected="false">Wiadomości</a>
            <a class="nav-link" id="v-pills-logout-tab" href="${pageContext.request.contextPath}/logout" role="tab"
               aria-controls="v-pills-logout" aria-selected="false">Wyloguj</a>

        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="false">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Informacja</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Wiadomość wysłana!
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

</div>