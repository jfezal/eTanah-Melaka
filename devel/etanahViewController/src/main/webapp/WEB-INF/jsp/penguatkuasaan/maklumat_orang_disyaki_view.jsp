<%--
    Document   : orang_disyaki
    Created on :
    Author     : nurshahida.radzi
    Modified    : ctzainal
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<c:if test="${multipleOp}">
    <style type="text/css">
        .tablecloth{
            padding:0px;
            width:60%;
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function viewOKS(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?viewOrangKenaSyak&idOrangKenaSyak='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600");
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatOrangDisyakiActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Orang Yang Disyaki
            </legend>
            <div class="content" align="center">
                <c:if test="${!multipleOp}">
                    <display:table class="tablecloth" name="${actionBean.permohonan.aduan.senaraiOrangKenaSyak}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <%--<display:column property="idOrangKenaSyak" title="ID OKS"></display:column>--%>
                        <display:column title="No.Pengenalan"><font style="text-transform: uppercase"><a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.noPengenalan}</a></font></display:column>
                        <display:column property="nama" title="Nama"></display:column>
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
                        <display:column title="Lampiran">
                            <p align="center">

                                <c:if test="${line.dokumen.namaFizikal != null}">

                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                         onclick="doViewReport('${line.dokumen.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>

                                </c:if>
                            </p>
                        </display:column>
                    </display:table>
                </c:if>
                <c:if test="${multipleOp}">
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="10%" cellpadding="1">
                                <tr>
                                    <td width="10%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="10%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                </tr>
                                <tr>
                                    <td width="10%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="10%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="10%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="10%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                    </td>
                                </tr>

                            </table>
                        </display:column>
                        <display:column title="Maklumat Orang Disyaki">
                            <c:set value="1" var="count"/>

                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Nama</b></th>
                                    <th  width="5%" align="center"><b>Papar</b></th>

                                </tr>
                                <c:forEach items="${line.senaraiAduanOrangKenaSyak}" var="oks">
                                    <c:if test="${actionBean.statusIP}">
                                        <c:if test="${oks.permohonanAduanOrangKenaSyak.idPermohonan eq actionBean.permohonan.idPermohonan}">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="50%"><a class="popup" onclick="viewOKS(${oks.idOrangKenaSyak})">${oks.nama}</a></td>

                                                <td width="30%">
                                                    <c:if test="${oks.dokumen.namaFizikal != null}">
                                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                             onclick="doViewReport('${oks.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${oks.dokumen.kodDokumen.nama}"/>
                                                    </c:if>

                                                </td>

                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${!actionBean.statusIP}">
                                        <tr>
                                            <td width="5%">${count}</td>
                                            <td width="50%"><a class="popup" onclick="viewOKS(${oks.idOrangKenaSyak})">${oks.nama}</a></td>

                                            <td width="30%">
                                                <c:if test="${oks.dokumen.namaFizikal != null}">
                                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                         onclick="doViewReport('${oks.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${oks.dokumen.kodDokumen.nama}"/>
                                                </c:if>

                                            </td>

                                        </tr>
                                        <c:set value="${count+1}" var="count"/>
                                    </c:if>


                                </c:forEach>
                            </table>
                        </display:column>

                    </display:table>
                </c:if>

                <br>
            </div>
        </fieldset>
    </div>
</s:form>