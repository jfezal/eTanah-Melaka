<%-- 
    Document   : maklumat_borang_bongkar
    Created on : July 25, 2012, 10:00:00 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<style type="text/css">

    .tablecloth{
        padding:0px;
        width:70%;
    }

    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }
</style>


<script type="text/javascript">

    function addRecord(id){
        //window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupTambahLaporanOperasi", "eTanah",
        //"status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
        
        var url = "${pageContext.request.contextPath}/penguatkuasaan/borang_bongkar?popupTambahBorangBongkar&idOks="+id;

        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }

    function editRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupEditLaporanOperasi&idOperasi='+id;
        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
        //window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1100,height=600,scrollbars=yes");
    }

    
    function refreshPageBorangBongkar(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/borang_bongkar?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    
    function viewRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function viewBorangBongkar(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/borang_bongkar?popupViewBorangBongkar&idOks='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }




</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBorangBongkarActionBean">
    <s:messages />
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.opFlag == true}">

                <legend>
                    Maklumat Borang Bongkar
                </legend>
                <c:if test="${edit}">
                    <div class="instr-fieldset"><br>
                        Klik butang borang bongkar untuk masukkan maklumat bongkar
                    </div>
                    <br>
                    <div class="content" align="center">
                        <c:if test="${actionBean.opFlag == true}">
                            <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                                <display:column title="Bil" sortable="true" style="width:5px;">${line_rowNum}</display:column>
                                <display:column title="Maklumat Laporan Operasi" style="width:300px;">
                                    <table width="20%" cellpadding="1">
                                        <tr>
                                            <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                            <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                        </tr>
                                        <tr>
                                            <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                            <td width="20%">
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                        </tr>
                                        <tr>
                                            <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                            <td width="20%">
                                                <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                                <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                                <c:if test="${time eq 'AM'}">PAGI</c:if>
                                                <c:if test="${time eq 'PM'}">PETANG</c:if>
                                            </td>
                                        </tr>

                                    </table>
                                </display:column>
                                <display:column title="Maklumat Orang Disyaki" style="width:300px;">
                                    <c:set value="1" var="count"/>
                                    <c:if test="${fn:length(line.senaraiAduanOrangKenaSyak) ne 0}">
                                        <table width="70%" cellpadding="1">
                                            <tr>
                                                <th  width="5%" align="center"><b>Bil</b></th>
                                                <th  width="30%" align="center"><b>Nama</b></th>
                                                <th  width="30%" align="center"><b>Borang Bongkar</b></th>

                                            </tr>
                                            <c:forEach items="${line.senaraiAduanOrangKenaSyak}" var="oks">
                                                <tr>
                                                    <td width="5%">${count}</td>
                                                    <td width="30%"><u><a class="popup" onclick="viewBorangBongkar(${oks.idOrangKenaSyak})">${oks.nama}</a></u></td>
                                                    <td width="30%">
                                                        <div align="center">
                                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Kemasikini Borang Bongkar : ${oks.nama}" onclick="addRecord('${oks.idOrangKenaSyak}');"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                </display:column>


                            </display:table>
                        </c:if>
                        <br>

                        <%--<s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord();"/>--%>
                    </div>
                </c:if>

                <c:if test="${view}">
                    <br>
                    <div class="content" align="center">
                        <c:if test="${actionBean.opFlag == true}">
                            <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                                <display:column title="Bil" sortable="true" style="width:5%;">${line_rowNum}</display:column>
                                <display:column title="Maklumat Laporan Operasi" style="width:20%;">
                                    <table width="20%" cellpadding="1">
                                        <tr>
                                            <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                            <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                        </tr>
                                        <tr>
                                            <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                            <td width="20%">
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                        </tr>
                                        <tr>
                                            <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                            <td width="20%">
                                                <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                                <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                                <c:if test="${time eq 'AM'}">PAGI</c:if>
                                                <c:if test="${time eq 'PM'}">PETANG</c:if>
                                            </td>
                                        </tr>

                                    </table>
                                </display:column>
                                <display:column title="Maklumat Orang Disyaki" style="width:30%;">
                                    <c:set value="1" var="count"/>
                                    <c:if test="${fn:length(line.senaraiAduanOrangKenaSyak) ne 0}">
                                        <table width="70%" cellpadding="1">
                                            <tr>
                                                <th  width="1%" align="center"><b>Bil</b></th>
                                                <th  width="1%" align="center"><b>Nama</b></th>
                                                <th  width="5%" align="center"><b>Borang Bongkar</b></th>

                                            </tr>
                                            <c:forEach items="${line.senaraiAduanOrangKenaSyak}" var="oks">
                                                <c:if test="${actionBean.statusIP}">
                                                    <c:if test="${oks.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan}">
                                                        <tr>
                                                            <td width="5%">${count}</td>
                                                            <td width="40%">${oks.nama}</td>
                                                            <td width="30%">
                                                                <div align="center">
                                                                    <img alt='Klik Untuk Papar' src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                         onclick="viewBorangBongkar(${oks.idOrangKenaSyak})" height="30" width="30" alt="papar"
                                                                         onmouseover="this.style.cursor='pointer';" title="Papar Borang Bongkar ${oks.nama}"/>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <c:set value="${count+1}" var="count"/>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${!actionBean.statusIP}">
                                                    <tr>
                                                        <td width="5%">${count}</td>
                                                        <td width="40%">${oks.nama}</td>
                                                        <td width="30%">
                                                            <div align="center">
                                                                <img alt='Klik Untuk Papar' src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                     onclick="viewBorangBongkar(${oks.idOrangKenaSyak})" height="30" width="30" alt="papar"
                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Borang Bongkar ${oks.nama}"/>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <c:set value="${count+1}" var="count"/>
                                                </c:if>

                                            </c:forEach>
                                        </table>
                                    </c:if>
                                </display:column>


                            </display:table>
                        </c:if>
                        <br>
                    </div>
                </c:if>
            </c:if>



        </fieldset>
    </div>
</s:form>

