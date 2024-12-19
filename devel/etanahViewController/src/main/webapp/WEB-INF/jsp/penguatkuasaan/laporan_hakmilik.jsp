<%-- 
    Document   : laporan_hakmilik
    Created on : Oct 5, 2012, 12:00:24 PM
    Author     : Siti Fariza Hanim
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:150px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>

<script type="text/javascript">
    $(document).ready(function() {
        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }

    });
</script>

<s:form  beanclass="etanah.view.penguatkuasaan.LaporanTanahActionBean">
    <div class="subtitle" align="center">

        <fieldset class="aras1">
            <legend>
                Hakmilik
            </legend>
            <div>
                <c:if test="${actionBean.multipleHakmilik eq false}">
                    <table border="0" align="center">
                        <tr> <td>&nbsp;</td></tr>
                        <tr><td id="tdLabel">ID Hakmilik :&nbsp;</td>
                            <td id="tdDisplay"> <c:if test="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik ne null}"> ${actionBean.hakmilikPermohonan.hakmilik.idHakmilik} </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik eq null}">Tiada Data</c:if>
                                </td>
                            </tr>

                            <tr><td id="tdLabel">Daerah :&nbsp;</td>
                                <td id="tdDisplay">
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.daerah.nama ne null}"> ${actionBean.hakmilikPermohonan.hakmilik.daerah.nama} </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.daerah.nama eq null}">Tiada Data</c:if>
                                </td>
                            </tr>

                        <%--<tr><td id="tdLabel">Seksyen :&nbsp;</td>
                            <td id="tdDisplay">
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.seksyen.nama ne null}"> ${actionBean.hakmilikPermohonan.hakmilik.seksyen.nama} </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.seksyen.nama eq null}">Tiada Data</c:if>
                            </td>
                        </tr>--%>

                        <tr><td id="tdLabel">Bandar/Pekan/Mukim :&nbsp;</td>
                            <td id="tdDisplay">
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama ne null}"> ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama} </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama eq null}">Tiada Data</c:if>
                                </td>
                            </tr>
                            <tr><td id="tdLabel">Jenis Hakmilik :&nbsp;</td>
                                <td id="tdDisplay">
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.nama ne null}"> ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.nama} </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.nama eq null}">Tiada Data</c:if>
                                </td>
                            </tr>
                            <tr><td id="tdLabel">Nombor Lot/PT :&nbsp;</td>
                                <td id="tdDisplay">

                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.noLot ne null}">
                                    <c:if test="${actionBean.hakmilik.noLot eq 'Tiada' || actionBean.hakmilikPermohonan.hakmilik.noLot  eq 'X'}">
                                        ${actionBean.hakmilikPermohonan.hakmilik.noLot}&nbsp; : &nbsp ${actionBean.hakmilikPermohonan.hakmilik.lot.nama}&nbsp;
                                    </c:if>
                                    <c:if test="${actionBean.hakmilikPermohonan.hakmilik.noLot ne 'Tiada' && actionBean.hakmilikPermohonan.hakmilik.noLot  ne 'X'}">
                                        <fmt:parseNumber value="${actionBean.hakmilikPermohonan.hakmilik.noLot}"/>&nbsp; : &nbsp ${actionBean.hakmilikPermohonan.hakmilik.lot.nama}&nbsp;
                                    </c:if>
                                </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.noLot eq null}">Tiada Data</c:if>
                                </td>
                            </tr>
                            <tr><td id="tdLabel">Kategori Tanah :&nbsp;</td>
                                <td id="tdDisplay">
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.kategoriTanah.nama ne null}"> ${actionBean.hakmilikPermohonan.hakmilik.kategoriTanah.nama} </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.kategoriTanah.nama eq null}">Tiada Data</c:if>
                                </td>
                            </tr>
                            <tr><td id="tdLabel">Tarikh Daftar :&nbsp;</td>
                                <td id="tdDisplay">
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.tarikhDaftar ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilikPermohonan.hakmilik.tarikhDaftar}"/>&nbsp;</c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.tarikhDaftar eq null}">Tiada Data</c:if>
                                </td>
                            </tr>
                            <tr><td id="tdLabel">Status Hakmilik :&nbsp;</td>
                                <td id="tdDisplay">
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.kodStatusHakmilik.nama ne null}"> ${actionBean.hakmilikPermohonan.hakmilik.kodStatusHakmilik.nama} </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.kodStatusHakmilik.nama eq null}">Tiada Data</c:if>
                                </td>
                            </tr>
                            <tr><td id="tdLabel">Keluasan :&nbsp;</td>
                                <td id="tdDisplay">
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.luas ne null}"> <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama} </c:if>
                                <c:if test="${actionBean.hakmilikPermohonan.hakmilik.luas eq null}">Tiada Data</c:if>
                                </td>
                            </tr>

                        </table><br/>
                    </div>
                    <legend>Hakmilik Urusan </legend>
                    <div>
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikUrusan}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column property="kodUrusan.nama" title="Urusan" ></display:column>
                        <display:column title="Tarikh Daftar"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhDaftar}"/></display:column>

                        <display:column title="Status">
                            <c:if test="${line.aktif eq 'Y'}">Aktif</c:if>
                            <c:if test="${line.aktif eq 'T'}"> Tidak Aktif</c:if>
                            <c:if test="${line.aktif eq null}">Tiada Data</c:if>
                        </display:column>
                        <display:column title="Tarikh Batal"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhBatal}"/></display:column>
                    </display:table>
                </div>
            </c:if>

            <%--<div class="content" align="center">
               <display:table name="${actionBean.permohonan.senaraiFasa}" class="tablecloth">
                   <display:column property="infoAudit.tarikhMasuk" title="Tarikh" sortable="true" format="{0,date,dd/MM/yyyy hh:mm aa}"/>
                   <display:column property="infoAudit.dimasukOleh.nama" title="Diproses Oleh"/>
                   <display:column property="idAliran" title="ID Aliran"/>
                   <display:column property="keputusan.nama" title="Status"/>
                   <display:column property="ulasan" title="Ulasan"/>

                </display:table>
            </div>--%>
            <c:if test="${actionBean.multipleHakmilik eq true}">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">
                        <c:if test="${line.hakmilik.idHakmilik eq null}">
                            Tiada rekod
                        </c:if>
                        <c:if test="${line.hakmilik.idHakmilik ne null}">
                            ${line_rowNum}
                        </c:if>
                    </display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.kodHakmilik.nama" title="Jenis hakmilik" />
                    <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                    <display:column title="Keluasan" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.kategoriTanah.nama" title="Kategori tanah"/>
                    <display:column title="Tarikh Daftar">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.hakmilik.tarikhDaftar}"/>
                    </display:column>
                    <display:column property="hakmilik.kodStatusHakmilik.nama" title="Status hakmilik" />
                </display:table>
                <br><br>
                <legend>Hakmilik Urusan </legend>
                <div>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                        <display:column property="hakmilik.idHakmilik" title="No. Hakmilik"/>
                        <display:column title="Urusan" >
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.senaraiHakmilikUrusan}" var="senarai">
                                <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${senarai.kodUrusan.nama}"/>&nbsp;&nbsp;<br>
                                    <c:set value="${count + 1}" var="count"/>
                                    <br>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Tarikh Daftar" >
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.senaraiHakmilikUrusan}" var="senarai">
                                <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${senarai.tarikhDaftar}"/>&nbsp;&nbsp;<br>
                                    <c:set value="${count + 1}" var="count"/>
                                    <br>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Status" >
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.senaraiHakmilikUrusan}" var="senarai">
                                <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:if test="${senarai.aktif eq 'Y'}">Aktif</c:if>
                                    <c:if test="${senarai.aktif eq 'T'}"> Tidak Aktif</c:if>
                                    <c:if test="${senarai.aktif eq null}">Tiada Data</c:if>&nbsp;&nbsp;<br>
                                    <c:set value="${count + 1}" var="count"/>
                                    <br>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Tarikh Batal" >
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.senaraiHakmilikUrusan}" var="senarai">
                                <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${senarai.tarikhBatal}"/>&nbsp;&nbsp;<br>
                                    <c:set value="${count + 1}" var="count"/>
                                    <br>
                                </c:if>
                            </c:forEach>
                        </display:column>

                    </display:table>
                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>