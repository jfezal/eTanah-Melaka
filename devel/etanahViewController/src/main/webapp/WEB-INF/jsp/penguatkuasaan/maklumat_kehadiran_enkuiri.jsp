<%-- 
    Document   : maklumat_kehadiran_enkuiri
    Created on : June 8, 2011, 3:50:00 PM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">

    //
    //    $(document).ready(function() {
    //        var bil =  ${fn:length(actionBean.senaraiPihakHadir)};
    //        for (var i = 0; i < bil; i++){
    //            if($("#statusHadir"+i).val() == "Y"){
    //                document.getElementById("ya"+i).checked = true;
    //            }
    //            else if($("#statusHadir"+i).val() == "T"){
    //                document.getElementById("tidak"+i).checked = true;
    //            }
    //        }
    //    });
  

    function show_calendar (){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_enkuiri?showForm3", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200,scrollbars=1");
    }
    function validation() {
        if($("#datepicker").val() == ""){
            alert('Sila masukkan " Tarikh Enkuiri " terlebih dahulu.');
            $("#datepicker").focus();
            return false;
        }

        if($("#jam").val() == ""){
            alert('Sila pilih " Jam " terlebih dahulu.');
            $("#jam").focus();
            return false;
        }

        if($("#minit").val() == ""){
            alert('Sila Pilih " Minit " terlebih dahulu.');
            $("#minit").focus();
            return false;
        }

        if($("#ampm").val() == ""){
            alert('Sila Pilih " AM/PM "');
            $("#ampm").focus();
            return false;
        }
        
        if($("#tempat").val() == ""){
            alert('Sila masukkan " Tempat " terlebih dahulu.');
            $("#tempat").focus();
            return false;
        }
        
        return true;
    }

    function test(f) {
        $(f).clearForm();
    }

    function removeKehadiran(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            $.get('${pageContext.request.contextPath}/penguatkuasaan/maklumat_enkuiri?removeList&id='+id,
            function(html){
                $("#kehadiranDiv").replaceWith($('#kehadiranDiv', $(html)));

            }, 'html');
        }
    }

    function viewDetail(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_enkuiri?viewKehadiranDetail&idEnkuiri='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    
    function addRecord(){
        var id = $("#idEnkuiri").val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_enkuiri?addKehadiran&idEnkuiri='+id;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=600, left=" + left + ",top=" + top);
    }
    
    function refreshListKehadiran(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_enkuiri?refreshpage';
        $.get(url,
        function(data){
            $("#kehadiranEnkuiriDiv").replaceWith($('#kehadiranEnkuiriDiv', $(data)));
        },'html');
    }
    
    function editRecord(i){
        var id = $("#idEnkuiri").val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_enkuiri?editKehadiran&idEnkuiri='+id+'&idKehadiran='+i;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=600, left=" + left + ",top=" + top);
    }
    
    function removeRecord(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_enkuiri?deleteRecorKehadiran&idKehadiran='+id;
            $.get(url,
            function(data){
                $("#kehadiranEnkuiriDiv").replaceWith($('#kehadiranEnkuiriDiv', $(data)));
            },'html');}
    }
    
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="maklumat_laporan_enkuiri" beanclass="etanah.view.penguatkuasaan.EnkuiriKehadiranActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat kehadiran Enkuiri
                <s:hidden name="enkuiri.idEnkuiri" id="idEnkuiri"/>&nbsp;
            </legend>
            <c:if test="${empty actionBean.enkuiri.idEnkuiri}">
                <div class="content">
                    <p>
                        <label>Tarikh :</label>
                        Tiada data&nbsp;
                    </p>
                    <p>
                        <label>Masa :</label>
                        Tiada data&nbsp;

                    <p>
                        <label>Hari :</label>
                        Tiada data&nbsp;
                    </p>
                    <p>
                        <label>Tempat :</label>
                        Tiada data&nbsp;
                    </p>

                </div>

                <fieldset class="aras1">
                    <legend>
                        Pihak Terlibat
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiPihakHadir}" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="Nama" property="nama" style="text-transform: uppercase"></display:column>
                            <display:column title="No.Pengenalan" property="noPengenalan"></display:column>
                            <display:column title="Status Kehadiran">
                                <c:if test="${line.hadir eq 'T'}">Tidak Hadir</c:if>
                                <c:if test="${line.hadir eq 'Y'}">Hadir</c:if>
                            </display:column>

                        </display:table>
                        <p>&nbsp;</p>
                    </div>
                    <legend>
                        Keputusan Enkuiri
                    </legend>
                    <div align="center">
                        <p>
                            <s:textarea id="ulasan" name="ulasan" cols="80" rows="10" readonly="true" />
                        </p>
                    </div>
                </fieldset>
            </c:if>
            <c:if test="${not empty actionBean.enkuiri.idEnkuiri}">
                <div class="content">
                    <p>
                        <label>Tarikh :</label>
                        ${actionBean.tarikhMula}&nbsp;
                    </p>
                    <p>
                        <label>Masa :</label>
                        ${actionBean.jam}:${actionBean.minit} ${actionBean.ampm} &nbsp;

                    <p>
                        <label>Hari :</label>
                        <fmt:formatDate pattern="EEEE" value="${actionBean.enkuiri.tarikhMula}"/>&nbsp;
                    </p>
                    <p>
                        <label>Tempat :</label>
                        ${actionBean.tempat} &nbsp;
                    </p>

                </div>

                <br>

                <fieldset class="aras1">
                    <legend>
                        Pihak Terlibat
                    </legend>
                    <div id="kehadiranEnkuiriDiv">
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiPihakHadir}" cellpadding="0" cellspacing="0" id="line">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column title="Nama" property="nama" style="text-transform: uppercase"></display:column>
                                <display:column title="No.Pengenalan" property="noPengenalan"></display:column>
                                <display:column title="Status Kehadiran">
                                    <c:if test="${line.hadir eq 'T'}">Tidak Hadir</c:if>
                                    <c:if test="${line.hadir eq 'Y'}">Hadir</c:if>
                                </display:column>
                                <display:column title="Alamat" style="text-transform: uppercase">${line.alamat.alamat1}
                                    <c:if test="${line.alamat.alamat2 ne null}"> , </c:if>
                                    ${line.alamat.alamat2}
                                    <c:if test="${line.alamat.alamat3 ne null}"> , </c:if>
                                    ${line.alamat.alamat3}
                                    <c:if test="${line.alamat.alamat4 ne null}"> , </c:if>
                                    ${line.alamat.alamat4}
                                    ${line.alamat.poskod}
                                    ${line.alamat.negeri.nama}
                                </display:column>
                                <display:column title="Kemaskini">
                                    <div align="center">
                                        <%--<c:if test="${line.noRujukan eq 'L'}">--%>
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editRecord('${line.idKehadiran}')"/>
                                        <%--</c:if>--%>
                                    </div>
                                </display:column>
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeRecord('${line.idKehadiran}');"/>
                                    </div>
                                </display:column>
                            </display:table>
                            <p>&nbsp;</p>
                        </div>
                    </div>


                    <p align="center">
                        <s:button name="addKehadiran" id="addKehadiran" value="Tambah" class="btn" onclick="addRecord();"/>
                    </p>
                    <legend>
                        Keputusan Enkuiri
                    </legend>
                    <div align="center">
                        <p>
                            <s:textarea id="ulasan" name="ulasan" cols="80" rows="10" />
                        </p>
                    </div>
                </fieldset>
                <%--
                <c:if test="${fn:length(actionBean.senaraiPihakHadir) > 0}">
                    <fieldset class="aras1">
                        <legend>
                            Pihak Terlibat
                        </legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiPihakHadir}" cellpadding="0" cellspacing="0" id="line">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column title="Nama" property="nama"></display:column>
                                <display:column title="No.Pengenalan" property="noPengenalan"></display:column>
                                <display:column title="Kehadiran">
                                    <input type="radio" name="kehadiran${line_rowNum-1}" value="Y" id="ya${line_rowNum-1}"/> Hadir
                                    <input type="radio" name="kehadiran${line_rowNum-1}" value="T" id="tidak${line_rowNum-1}"/> Tidak Hadir &nbsp;
                                    <s:hidden name="senaraiPihakHadir[${line_rowNum-1}].hadir" value="${line.hadir}" id="statusHadir${line_rowNum-1}"/>
                                </display:column>
                                <display:column title="Catatan">
                                    <s:textarea cols="50" rows="5" name="catatan${line_rowNum-1}" id="catatan" value="${line.catatan}"/>
                                </display:column>
                            </display:table>
                            <p>&nbsp;</p>
                        </div>
                        <legend>
                            Keputusan Enkuiri
                        </legend>
                        <div align="center">
                            <p>
                                <s:textarea id="ulasan" name="ulasan" cols="80" rows="10"/>
                            </p>
                        </div>
                    </fieldset>
                </c:if>
                --%>



                <c:if test="${actionBean.stageId eq 'semak_laporan2' || actionBean.stageId eq 'semak2_laporan2'}">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>
                                Sejarah Enkuiri
                            </legend>
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri}" cellpadding="0" cellspacing="0" id="line1">
                                    <display:column title="Bil">${line1_rowNum}</display:column>
                                    <display:column title="Tarikh">
                                        <a class="popup" onclick="viewDetail(${line1.idEnkuiri})"><fmt:formatDate pattern="dd/MM/yyyy" value="${line1.tarikhMula}"/></a>&nbsp;
                                    </display:column>
                                    <display:column title="Masa">
                                        <fmt:formatDate pattern="hh:mm aaa" value="${line1.tarikhMula}"/>&nbsp;
                                    </display:column>
                                    <display:column title="Hari"></display:column>
                                    <display:column title="Tempat" property="tempat"></display:column>
                                </display:table>
                                <p>&nbsp;</p>
                            </div>
                        </fieldset>
                    </div>
                    <legend>
                        Keputusan Enkuiri
                    </legend>
                    <div align="center">
                        <p>
                            <s:textarea id="ulasan" name="ulasan" cols="80" rows="10" readonly="true" />
                        </p>
                    </div>
                </c:if>


                <p align="center">
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>

        </fieldset>


    </div>
</s:form>
