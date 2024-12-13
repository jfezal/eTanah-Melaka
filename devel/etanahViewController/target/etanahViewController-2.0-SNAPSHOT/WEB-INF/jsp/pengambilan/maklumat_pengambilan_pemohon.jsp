<%-- 
    Document   : maklumat_pengambilan_pemohon
    Created on : 05-Aug-2010, 18:59:23
    Author     : nordiyana
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.empty').remove();

        var len2 = $(".alamat").length;

        for (var i = 0; i < len2; i++) {
            var d = $('.x' + i).val();

            $('.nama' + i).bind('click', d, function() {
                window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?showEditPemohon&idPihak=" + d, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
            });
        }

    <%--$('#copy').click( function(){
        $('#page_div').html('');
        var url = '${pageContext.request.contextPath}/common/mohon_pihak?copy';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    });--%>
        });

        function popupExisting() {
            window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

        function addNew() {
            window.open("${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan2?pihakKepentinganPopup", "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function addNewPemohon() {
            window.open("${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan2?pemohonPopup", "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function copy() {
            $('#page_div').html('');
            var url = '${pageContext.request.contextPath}/common/mohon_pihak?copy';
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }


        function addRowMohonPihak(nama, kp, syer) {
            //TODO: to be complete
            var rowNo = $('table#lineMP tr').length;
            $('table#lineMP > tbody').append("<tr id='x" + rowNo + "' class='x'><td class='rowNo'>" + rowNo
                    + "</td><td>" + nama + "</td><td>" + kp + "</td><td>" + syer + "</td><td>" +
                    "<img alt='Click to Delete' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem" +
                    rowNo + "' onclick=\"remove(this.id,'line')\"></td></tr>");
        }

        function remove(val) {
            if (confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete&idPermohonanPihak=' + val;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function removePemohon(val) {
            if (confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan2?deletePemohon&idPemohon=' + val;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function removeChanges(val) {
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan2?deleteChanges&idKkini=' + val;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                            self.opener.refreshPageUlasanJabatanTeknikal();
                        });
            }
        }

        function addPemohon() {
            var len = $('.nama').length;
            for (var i = 1; i <= len; i++) {
                var ckd = $('#chkbox' + i).is(":checked");
                if (ckd) {
                    var url = '${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan2?simpanPemohon&idPihak=' + $('#chkbox' + i).val();
                    $.get(url,
                            function(data) {
                                $('#page_div').html(data);
                            });
                }
            }
        }

        function semakSyer(f) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan2?semakSyer', q,
                    function(data) {
                        if (data != '') {
                            alert(data);
                        }
                    }, 'html');
        }

        function dopopup(i, kod) {
            if (kod == "TN") {
                var url = "showEditNamaPemohon";
            }
            else if (kod == "TA") {
                var url = "showEditAlamatPemohon";
            } else {
                var url = "showEditPemohon";
            }
            var d = $('.x' + i).val();
            window.open("${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan2?" + url + "&idPihak=" + d, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }

</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.pengambilan.pbtActionBean">
        <%-- <fieldset class="aras1">
             <legend>Senarai Pihak Berkepentingan</legend>
             <div class="content" align="center">
                 <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList2}" cellpadding="0" cellspacing="0" id="line"
                                requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                     <c:if test="${edit}">
                         <display:column>
                             <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}"/>
                         </display:column>
                     </c:if>
                     <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                     <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                     <display:column title="Tuan Tanah">
                          <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                          <c:if test="${senarai.jenis.kod eq 'PM'}">
                              <c:out value="${senarai.pihak.nama}"/><br>
                              No.KP/Co:<c:out value="${senarai.pihak.noPengenalan}"/><br>
                          </c:if>
                          </c:forEach>
                     </display:column>
                     <display:column title="Pihak Berkepentingan">
                          <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                              <c:if test="${senarai.jenis.kod ne 'PM'}">
                                 <c:out value="${senarai.pihak.nama}"/><br>
                                 No.KP/Co:<c:out value="${senarai.pihak.noPengenalan}"/><br>
                              </c:if>
                          </c:forEach>
                     </display:column>
                     <display:column title="Jenis Pihak">
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <c:if test="${senarai.jenis.kod ne 'PM'}">
                                <c:out value="${senarai.jenis.nama}"/>
                            </c:if>
                        </c:forEach>
                     </display:column>
                     <display:column property="pihak.nama" title="Nama" class="nama"/>
                     <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                     <display:column title="Nombor Pengenalan">
                         <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <c:out value="${senarai.pihak.noPengenalan}"/><br>
                         </c:forEach>
                     </display:column>
                     <display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>
                     <display:column title="Bahagian yang dimiliki">
                         <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>
                                (<c:out value="${senarai.jenis.nama}"/>)<br>
                         </c:forEach>
                     </display:column>
                     <display:column title="Tarikh Pemilikan Didaftar">
                         <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                     </display:column>

                    </display:table>
                </div>
                    <c:if test="${edit}">
                        <p>
                            <label>&nbsp;</label>
                            <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addPemohon();"/>&nbsp;
                        </p>
                    </c:if>
            </fieldset>
            <br/>--%>

        <fieldset class="aras1">
            <legend>
                Senarai Pemohon
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <c:if test="${line.pihak.nama eq null}">
                        <display:column title="Nama">TIDAK DINYATAKAN</display:column></c:if>
                     <c:if test="${line.pihak.nama ne null}">
                         <display:column property="pihak.nama" title="Nama"></display:column></c:if>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Alamat" >${line.pihak.suratAlamat1}
                        <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat2}
                        <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat3}
                        <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat4}</display:column>
                    <display:column property="pihak.suratPoskod" title="Poskod" />
                    <display:column property="pihak.suratNegeri.nama" title="Negeri" />
                    <display:column property="pihak.noTelefon1" title="No.Telefon" />
                    <display:column property="pihak.noTelefon2" title="No.Fax" />
                    <display:column property="pihak.email" title="Email" />

                    <c:if test="${edit}">
                        <display:column title="Kemaskini"><a href="#" src='${pageContext.request.contextPath}/images/edit.gif' onclick="dopopup('${line_rowNum -1}', '${actionBean.p.kodUrusan.kod}');
            return false;">Kemaskini</a></display:column>

                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')">
                            </div>
                        </display:column>
                    </c:if>
                </display:table>
            </div>
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
            </p>
        </fieldset>
        <br/>

    </s:form>
</div>