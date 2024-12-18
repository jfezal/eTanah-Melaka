<%--
    Document   : Notis Sita
    Created on : July 14, 2012, 5:16:37 PM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<c:if test="${multipleOp}">
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

        .infoHeader{
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;
            text-align: center;

        }

    </style>
</c:if>
<script type="text/javascript">
    function popupDetail(idBarang){
        //alert(idBarang)
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?editBarangRampasan&idBarang='+idBarang;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");


    }

    function popup(idBarang){
        //alert(idBarang)
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?viewBarangDetail&idBarang='+idBarang;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=890,height=600,scrollbars=yes");

    }

    function popupNotisSita(idBarang){
        //alert(idBarang)
        
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?editPopupNotisSita&idBarang='+idBarang;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);

    }

    function popupViewNotisSita(idBarang){
        //alert(idBarang)
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?viewPopupNotisSita&idBarang='+idBarang;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);

    }

    function refreshNotisSita(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?refreshpageNotis';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
            
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBarangTahananActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.opFlag == true}">
                <legend>
                    Maklumat Barang Rampasan 
                </legend>
                <div class="content" align="center">
                    <c:if test="${!multipleOp}">
                        <c:if test="${edit}">
                            <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="/penguatkuasaan/maklumat_barang_tahanan">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column title="Item"><u><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a></u>&nbsp;&nbsp;${line.nomborPendaftaran}</display:column>
                                <display:column title="Kuantiti" property="kuantiti"></display:column>
                                <display:column title="Lokasi Tangkapan" property="tempatTangkap"></display:column>
                                <display:column title="Notis Kesalahan">
                                    <div align="center">
                                        <img alt='Klik Untuk Masukkan Maklumat' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupNotisSita('${line.idBarang}')"/>
                                    </div>
                                </display:column>
                            </display:table>
                        </c:if>
                        <c:if test="${view}">
                            <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="/penguatkuasaan/maklumat_barang_tahanan">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column title="Item"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a>&nbsp;&nbsp;${line.nomborPendaftaran}</display:column>
                                <display:column title="Kuantiti" property="kuantiti"></display:column>
                                <display:column title="Lokasi Tangkapan" property="tempatTangkap"></display:column>
                                <display:column title="Notis Kesalahan">
                                    <div align="center">
                                        <img alt='Klik Untuk Paparan' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupViewNotisSita('${line.idBarang}')"/>
                                    </div>
                                </display:column>
                            </display:table>
                        </c:if>
                    </c:if>

                    <c:if test="${multipleOp}">
                        <c:if test="${edit}">
                            <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Maklumat Laporan Operasi">
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
                                <display:column title="Maklumat Barang Rampasan">
                                    <c:set value="1" var="count"/>
                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="1%" align="center"><b>Item</b></th>
                                            <th  width="1%" align="center"><b>Kuantiti</b></th>
                                            <th  width="3%" align="center"><b>Lokasi Tangkapan</b></th>
                                            <th  width="5%" align="center"><b>Notis Kesalahan</b></th>

                                        </tr>
                                        <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                            <c:if test="${actionBean.statusIP}">
                                                <c:if test="${barang.aduanOrangKenaSyak.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan}">
                                                    <tr>
                                                        <td width="5%">${count}</td>
                                                        <td width="50%"><u><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></u>&nbsp;&nbsp;${barang.nomborPendaftaran}</td>
                                                        <td width="5%">
                                                            <c:if test="${barang.kodKategoriItemRampasan.kod eq 'K'}">
                                                                1
                                                            </c:if>
                                                            <c:if test="${barang.kodKategoriItemRampasan.kod eq 'B'}">
                                                                ${barang.kuantiti}  ${barang.kuantitiUnit}
                                                            </c:if>
                                                        </td>
                                                        <td width="50%">${barang.tempatTangkap}</td>
                                                        <td width="30%">
                                                            <div align="center">
                                                                <img alt='Klik Untuk Masukkan Maklumat' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupNotisSita('${barang.idBarang}')"/>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <c:set value="${count+1}" var="count"/>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${!actionBean.statusIP}">
                                                <tr>
                                                    <td width="5%">${count}</td>
                                                    <td width="50%"><u><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></u>&nbsp;&nbsp;${barang.nomborPendaftaran}</td>
                                                    <td width="5%">
                                                        <c:if test="${barang.kodKategoriItemRampasan.kod eq 'K'}">
                                                            1
                                                        </c:if>
                                                        <c:if test="${barang.kodKategoriItemRampasan.kod eq 'B'}">
                                                            ${barang.kuantiti}  ${barang.kuantitiUnit}
                                                        </c:if>
                                                    </td>
                                                    <td width="50%">${barang.tempatTangkap}</td>
                                                    <td width="30%">
                                                        <div align="center">
                                                            <img alt='Klik Untuk Masukkan Maklumat' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupNotisSita('${barang.idBarang}')"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:forEach>

                                    </table>
                                    <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                                </display:column>
                            </display:table>
                        </c:if>

                        <c:if test="${view}">
                            <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Maklumat Laporan Operasi">
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
                                <display:column title="Maklumat Barang Rampasan">
                                    <c:set value="1" var="count"/>

                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="1%" align="center"><b>Item</b></th>
                                            <th  width="1%" align="center"><b>Kuantiti</b></th>
                                            <th  width="3%" align="center"><b>Lokasi Tangkapan</b></th>
                                            <th  width="5%" align="center"><b>Notis Kesalahan</b></th>

                                        </tr>
                                        <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                            <c:if test="${actionBean.statusIP}">
                                                <c:if test="${barang.aduanOrangKenaSyak.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan}">
                                                    <tr>
                                                        <td width="5%">${count}</td>
                                                        <td width="50%"><u><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></u>&nbsp;&nbsp;${barang.nomborPendaftaran}</td>
                                                        <td width="5%">
                                                            <c:if test="${barang.kodKategoriItemRampasan.kod eq 'K'}">
                                                                1
                                                            </c:if>
                                                            <c:if test="${barang.kodKategoriItemRampasan.kod eq 'B'}">
                                                                ${barang.kuantiti} ${barang.kuantitiUnit}
                                                            </c:if>
                                                        </td>
                                                        <td width="50%">${barang.tempatTangkap}</td>
                                                        <td width="30%">
                                                            <div align="center">
                                                                <img alt='Klik Untuk Papar' src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                     onclick="popupViewNotisSita('${barang.idBarang}')" height="30" width="30" alt="papar"
                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Notis Sita ${barang.item}"/>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <c:set value="${count+1}" var="count"/>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${!actionBean.statusIP}">
                                                <tr>
                                                    <td width="5%">${count}</td>
                                                    <td width="50%"><u><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></u>&nbsp;&nbsp;${barang.nomborPendaftaran}</td>
                                                    <td width="5%">
                                                        <c:if test="${barang.kodKategoriItemRampasan.kod eq 'K'}">
                                                            1
                                                        </c:if>
                                                        <c:if test="${barang.kodKategoriItemRampasan.kod eq 'B'}">
                                                            ${barang.kuantiti} ${barang.kuantitiUnit}
                                                        </c:if>
                                                    </td>
                                                    <td width="50%">${barang.tempatTangkap}</td>
                                                    <td width="30%">
                                                        <div align="center">
                                                            <img alt='Klik Untuk Papar' src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                 onclick="popupViewNotisSita('${barang.idBarang}')" height="30" width="30" alt="papar"
                                                                 onmouseover="this.style.cursor='pointer';" title="Papar Notis Sita ${barang.item}"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>

                                        </c:forEach>

                                    </table>
                                    <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                                </display:column>
                            </display:table>
                        </c:if>
                    </c:if>

                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>