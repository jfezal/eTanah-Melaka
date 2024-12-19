<%--
    Document   : maklumat_barang_tahanan_view
    Created on : May 17, 2010, 5:16:37 PM
    Author     : nurshahida.radzi
    Modify by  : sitifariza.hanim (04012011)
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
                        <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/penguatkuasaan/maklumat_barang_tahanan">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="Barang yang Dirampas"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a>&nbsp;&nbsp;${line.nomborPendaftaran}</display:column>
                            <display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>
                            <display:column title="Status">
                                <c:if test ="${line.status.kod eq 'ST'}">
                                    Sudah dituntut
                                </c:if>
                                <c:if test ="${line.status.kod ne 'ST'}">
                                    ${line.status.nama}
                                </c:if>

                            </display:column>
                            <display:column title="Papar">
                                <c:if test="${line.imej.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${line.imej.idDokumen}');" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${line.imej.kodDokumen.nama}"/>
                                </c:if>
                            </display:column>

                        </display:table>
                    </c:if>

                    <c:if test="${multipleOp}">
                        <c:if test="${multipleOp}">
                            <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Maklumat Laporan Operasi">
                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                            <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                                            <th  width="5%" align="center"><b>Papar</b></th>

                                        </tr>
                                        <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                            <c:if test="${actionBean.statusIP}">
                                                <c:if test="${barang.aduanOrangKenaSyak.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan}">
                                                    <tr>
                                                        <td width="5%">${count}</td>
                                                        <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a>&nbsp;&nbsp;${barang.nomborPendaftaran}</td>
                                                        <td width="30%">
                                                            <c:if test="${barang.imej.namaFizikal != null}">
                                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                     onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/> 
                                                            </c:if>
                                                        </td>

                                                    </tr>
                                                    <c:set value="${count+1}" var="count"/>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${!actionBean.statusIP}">
                                                <c:choose>
                                                    <c:when test="${actionBean.stageLucutHak eq true}">
                                                        <c:if test="${barang.keputusan.kod eq 'LH'}">
                                                            <tr>
                                                                <td width="5%">${count}</td>
                                                                <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a>&nbsp;&nbsp;${barang.nomborPendaftaran}</td>
                                                                <td width="30%">
                                                                    <c:if test="${barang.imej.namaFizikal != null}">
                                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                             onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/> 
                                                                    </c:if>
                                                                </td>

                                                            </tr>
                                                        </c:if>
                                                    </c:when>
                                                    <c:when test="${actionBean.stageRujukMahkamah eq true}">
                                                        <c:if test="${barang.keputusan.kod eq 'RM'}">
                                                            <tr>
                                                                <td width="5%">${count}</td>
                                                                <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a>&nbsp;&nbsp;${barang.nomborPendaftaran}</td>
                                                                <td width="30%">
                                                                    <c:if test="${barang.imej.namaFizikal != null}">
                                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                             onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/> 
                                                                    </c:if>
                                                                </td>

                                                            </tr>
                                                        </c:if>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <tr>
                                                            <td width="5%">${count}</td>
                                                            <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a>&nbsp;&nbsp;${barang.nomborPendaftaran}</td>
                                                            <td width="30%">
                                                                <c:if test="${barang.imej.namaFizikal != null}">
                                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                                         onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/> 
                                                                </c:if>
                                                            </td>

                                                        </tr>
                                                    </c:otherwise>
                                                </c:choose>

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