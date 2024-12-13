<%--
    Document   : view_hakmilik_asal
    Created on : 13 April 2011, 3:41:04 PM
    Author     : sitifariza.hanim
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
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
        alert('test');
        maximizeWindow();
    });
</script>

<s:form  beanclass="etanah.view.penguatkuasaan.MukimIndeksActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Lama
            </legend>
            <table border="0">
                <tr> <td>&nbsp;</td></tr>
                <tr><td id="tdLabel">ID Hakmilik :&nbsp;</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.idHakmilik ne null}"> ${actionBean.hakmilik.idHakmilik} </c:if>
                        <c:if test="${actionBean.hakmilik.idHakmilik eq null}">Tiada Data</c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Daerah :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.daerah.nama ne null}"> ${actionBean.hakmilik.daerah.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.daerah.nama eq null}">Tiada Data</c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Seksyen :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.seksyen.nama ne null}"> ${actionBean.hakmilik.seksyen.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.seksyen.nama eq null}">Tiada Data</c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Bandar/Pekan/Mukim :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}"> ${actionBean.hakmilik.bandarPekanMukim.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Jenis Hakmilik :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.kodHakmilik.nama ne null}"> ${actionBean.hakmilik.kodHakmilik.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.kodHakmilik.nama eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Kategori Tanah :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}"> ${actionBean.hakmilik.kategoriTanah.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Kod Lot/PT :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.lot.nama ne null}"> ${actionBean.hakmilik.lot.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.lot.nama eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Nombor Lot/PT :&nbsp;</td>
                    <td id="tdDisplay">

                        <c:if test="${actionBean.hakmilik.noLot ne null}">
                            <c:if test="${actionBean.hakmilik.noLot eq 'Tiada' || actionBean.hakmilik.noLot  eq 'X'}">
                                ${actionBean.hakmilik.noLot}&nbsp;
                            </c:if>
                            <c:if test="${actionBean.hakmilik.noLot ne 'Tiada' && actionBean.hakmilik.noLot  ne 'X'}">
                                <fmt:parseNumber value="${actionBean.hakmilik.noLot}"/>&nbsp;
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.hakmilik.noLot eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Keluasan :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.luas ne null}"> <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.luas eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Syarat Nyata :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}"> ${actionBean.hakmilik.syaratNyata.syarat} </c:if>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Sekatan Kepentingan :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}"> ${actionBean.hakmilik.sekatanKepentingan.sekatan} </c:if>
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Nombor Pelan Piawai :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.noLitho ne null}"> ${actionBean.hakmilik.noLitho} </c:if>
                        <c:if test="${actionBean.hakmilik.noLitho eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Nombor Pelan :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.noPelan ne null}"> ${actionBean.hakmilik.noPelan} </c:if>
                        <c:if test="${actionBean.hakmilik.noPelan eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Jenis Rizab :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.rizab.nama ne null}"> ${actionBean.hakmilik.rizab.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.rizab.nama eq null}">Tiada Data</c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Cukai Tahunan (RM) :&nbsp;</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.cukai ne null}"><s:format formatPattern="##,##0.00" value="${actionBean.hakmilik.cukai}"/> </c:if>
                        <c:if test="${actionBean.hakmilik.cukai eq null}">Tiada Data</c:if>
                    </td>
                </tr>
            </table>
            <p align="center">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </div>
</s:form>