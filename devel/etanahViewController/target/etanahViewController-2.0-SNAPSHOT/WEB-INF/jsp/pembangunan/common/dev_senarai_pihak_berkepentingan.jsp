<%-- 
    Document   : dev_senarai_pihak_berkepentingan
    Created on : Jan 12, 2010, 5:55:32 PM
    Author     : nursyazwani
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
            window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?pihakKepentinganPopup", "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function addNewPemohon() {
            window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?pemohonPopup", "eTanah",
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
                var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?deletePemohon&idPemohon=' + val;
                $.get(url,
                        function(data) {
                            $('#page_div').html(data);
                        }, 'html');
            }
        }

        function removeChanges(val) {
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer) {
                var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?deleteChanges&idKkini=' + val;
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
                    var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?simpanPemohon&idPihak=' + $('#chkbox' + i).val();
                    $.get(url,
                            function(data) {
                                $('#page_div').html(data);
                            });
                }
            }
        }

        function semakSyer(f) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?semakSyer', q,
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
            window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?" + url + "&idPihak=" + d, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }

</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.pembangunan.PihakBerkepentinganActionBean">
        <fieldset class="aras1">
            <legend>Senarai Pihak Berkepentingan</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList2}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                    <display:column title="Bil" sortable="true" style="vertical-align:top">${line_rowNum}</display:column>
                    <display:column title="No. Hakmilik" style="vertical-align:top">${line.hakmilik.kodHakmilik.kod} <fmt:formatNumber  pattern="00" value="${line.hakmilik.noHakmilik}"/></display:column>
                    <display:column title="Tuan Tanah" style="vertical-align:top">

                        <display:table class="tablecloth" name="${line.hakmilik.senaraiPihakBerkepentingan}" cellpadding="0" cellspacing="0" id="line2">
                            <display:column title="Nama">                                    
                                <c:if test="${line2.jenis.kod eq 'PM' && line2.aktif eq 'Y'}">
                                    ${line2.nama}<br>                                         
                                    <c:choose>
                                        <c:when test="${line2.jenisPengenalan.kod eq 'S' ||line2.jenisPengenalan.kod eq 'D' ||line2.jenisPengenalan.kod eq 'N' || line2.jenisPengenalan.kod eq 'U'}">
                                            Syarikat: ${line2.noPengenalan}
                                        </c:when>
                                        <c:otherwise>
                                            No.KP: ${line2.noPengenalan}
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </display:column>
                            <display:column title="Bahagian Dimiliki">
                                <c:if test="${line2.jenis.kod eq 'PM' && line2.aktif eq 'Y'}">
                                    ${line2.syerPembilang}/${line2.syerPenyebut}
                                </c:if>
                            </display:column>
                        </display:table>
                    </display:column>

                    <display:column title="Pihak Berkepentingan" style="vertical-align:top">

                        <display:table class="tablecloth" name="${line.hakmilik.senaraiPihakBerkepentingan}" cellpadding="0" cellspacing="0" id="line2">
                            <display:column title="Nama">
                                <c:if test="${line2.jenis.kod ne 'PM' && line2.aktif eq 'Y'}">
                                    ${line2.nama}<br>                                        
                                    <c:choose>
                                        <c:when test="${line2.jenisPengenalan.kod eq 'S' ||line2.jenisPengenalan.kod eq 'D' ||line2.jenisPengenalan.kod eq 'N' || line2.jenisPengenalan.kod eq 'U'}">
                                            Syarikat: ${line2.noPengenalan}
                                        </c:when>
                                        <c:otherwise>
                                            No.KP: ${line2.noPengenalan}
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </display:column>
                            <display:column title="Jenis">
                                <c:if test="${line2.jenis.kod ne 'PM' && line2.aktif eq 'Y'}">
                                    ${line2.jenis.nama}
                                </c:if>
                            </display:column>
                        </display:table>
                    </display:column>

                    <display:column title="Tarikh Pemilikan Didaftar" style="vertical-align:top">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                    </display:column>
                </display:table>
            </div>

        </fieldset>
        <br/>


    </s:form>
</div>