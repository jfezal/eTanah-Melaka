<%-- 
    Document   : maklumat_asas_tanah_pengambilan
    Created on : 07-Jun-2010, 11:23:14
    Author     : nordiyana
--%>

<%--
    Document   : kemasukan_maklumat_asas
    Created on : 21 Oktober 2009, 3:36:26 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
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
    $(document).ready( function(){
        $(".wideselect")
        .mouseover(function(){
            $(this).data("origWidth", $(this).css("width")).css("width", "auto");
        })
        .mouseout(function(){
            $(this).css("width", $(this).data("origWidth"));
        });

        $("#noPu").val($("#hiddenNoPu").val());
        $("#syaratNyata").val($("#hiddenSyaratNyata").val());
        $("#sekatan").val($("#hiddenKodSekatan").val());

        $("#cariKodSyaratNyata").click(function(){
            var url ="${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSyaratNyata";
            window.open(url,"eTanah","status=1,toolbar=0,location=1,menubar=0,width=900,height=600");
        });

        $("#cariKodSekatan").click(function(){
            var url ="${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSekatan";
            window.open(url,"eTanah","status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        });

        if($("#pegangan").val()=='S'){
            $("#tempohPegangan").val('');
            $("#tempohPegangan").attr("disabled", true);
        }
        $("#pegangan").change( function() {
            if($("#pegangan").val()=='S'){
                $("#tempohPegangan").val('');
                $("#tempohPegangan").attr("disabled", true);
            }
            if($("#pegangan").val()=='P'){
                $("#tempohPegangan").attr("disabled", false);
            }
        });

    });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pengambilan.MaklumatHakmilikActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Asas</legend>
            
            <table border="0">
                <tr><td id="tdLabel">ID Hakmilik :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.idHakmilik ne null}"> ${actionBean.hakmilik.idHakmilik}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.idHakmilik eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Bandar/Pekan/Mukim :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}"> ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <%--<c:if test="${actionBean.stageId ne '01TrmArhn' and actionBean.stageId ne 'g_charting_keputusan' and actionBean.stageId ne '03ArhnUtkPnydianPU' and actionBean.stageId ne '04SedPermhnanSBU' and actionBean.stageId ne '05TrmSmkPermhnanSBU' and actionBean.stageId ne '06PrakuanPermhnanSBU' and actionBean.stageId ne '07TrmSBU' and actionBean.stageId ne '08ArhSedPU'  and actionBean.stageId ne '8aSedPU'}">--%>
                <tr><td id="tdLabel">Keluasan :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.luas ne null}">  <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.luas eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <%--</c:if>--%>
                <tr><td id="tdLabel">No Syit Piawai :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.noLitho ne null}"> ${actionBean.hakmilik.noLitho}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.noLitho eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Taraf Pegangan :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.pegangan eq 'P'}"> Pajakan </c:if>
                        <c:if test="${actionBean.hakmilik.pegangan eq 'S'}"> Selama-lamanya </c:if>
                        <c:if test="${actionBean.hakmilik.pegangan eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Tempoh :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">  ${actionBean.hakmilik.tempohPegangan} tahun </c:if>
                        <c:if test="${actionBean.hakmilik.pegangan eq 'S'}"> Tiada </c:if>
                        <c:if test="${actionBean.hakmilik.pegangan eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Tarikh Luput :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.tarikhLuput ne null}"> ${actionBean.hakmilik.tarikhLuput}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.tarikhLuput eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Kawasan Rizab :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.rizab.nama ne null}"> ${actionBean.hakmilik.rizab.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.rizab.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Tarikh Daftar :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.tarikhDaftar ne null}"> <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.tarikhDaftar eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <%--<c:if test="${actionBean.stageId ne '01TrmArhn' and actionBean.stageId ne 'g_charting_keputusan' and actionBean.stageId ne '03ArhnUtkPnydianPU' and actionBean.stageId ne '04SedPermhnanSBU' and actionBean.stageId ne '05TrmSmkPermhnanSBU' and actionBean.stageId ne '06PrakuanPermhnanSBU' and actionBean.stageId ne '07TrmSBU' and actionBean.stageId ne '08ArhSedPU' and actionBean.stageId ne '8aSedPU'}">--%>
                <tr><td id="tdLabel">Kod Lot/PT :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.lot.nama ne null}"> ${actionBean.hakmilik.lot.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.lot.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Nombor Lot/PT :</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.noLot ne null}">
                            ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot}
                        </c:if>
                        <c:if test="${actionBean.hakmilik.noLot eq null}">Tiada</c:if>
                    </td>
                </tr>
                <%--</c:if>--%>
                <tr><td id="tdLabel">Tempat :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.lokasi ne null}"> ${actionBean.hakmilik.lokasi}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.lokasi eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Daerah :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.daerah.nama ne null}"> ${actionBean.hakmilik.daerah.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.daerah.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <%--<c:if test="${actionBean.stageId ne '01TrmArhn' and actionBean.stageId ne 'g_charting_keputusan' and actionBean.stageId ne '03ArhnUtkPnydianPU' and actionBean.stageId ne '04SedPermhnanSBU' and actionBean.stageId ne '05TrmSmkPermhnanSBU' and actionBean.stageId ne '06PrakuanPermhnanSBU' and actionBean.stageId ne '07TrmSBU' and actionBean.stageId ne '08ArhSedPU'  and actionBean.stageId ne '8aSedPU'}">--%>
                 <tr><td id="tdLabel">Nombor Pelan Akui :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.noPelan ne null}"> ${actionBean.hakmilik.noPelan}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.noPelan eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <%--</c:if>--%>

<%--
                <tr><td id="tdLabel">Seksyen :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.seksyen.nama ne null}"> ${actionBean.hakmilik.seksyen.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.seksyen.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>--%>

              <%--  <tr><td id="tdLabel">Jenis Hakmilik :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.kodHakmilik.nama ne null}"> ${actionBean.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.kodHakmilik.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>--%>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}">
                <tr><td id="tdLabel">Cukai Tanah :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.cukai ne null}"> RM <s:format formatPattern="#,##0.00" value="${actionBean.hakmilik.cukai}"/>&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.cukai eq null}"> Tiada </c:if>
                    </td>
                </tr>
                </c:if>
                <tr><td id="tdLabel">Kategori Tanah :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}"> ${actionBean.hakmilik.kategoriTanah.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Syarat Nyata :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">  ${actionBean.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Sekatan Kepentingan :</td>
                    <td id="tdDisplay">  <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</c:if>
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada </c:if>
                    </td>
                </tr>

            </table>

            <%--<p>
                <label>Tarikh Daftar :</label>
                 <c:if test="${actionBean.hakmilik.tarikhDaftar ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp;</c:if>
                <c:if test="${actionBean.hakmilik.tarikhDaftar eq null}"> Tiada Data </c:if>
                <fmt:formatDate type="date"
                                pattern="dd/MM/yyyy"
                                value="${actionBean.p.senaraiHakmilik[0].hakmilik.tarikhDaftar}"/>&nbsp;
                <s:hidden name="tarikhDaftar"/>
                 <s:text name="tarikhDaftar" class="datepicker" value="${actionBean.p.senaraiHakmilik[0].hakmilik.tarikhDaftar}"  formatType="date"/>
            </p>
             <p>
                 <label>Jenis Hakmilik :</label>
                 ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
             </p>
             <p>
                <label>No Hakmilik :</label>
                ${actionBean.hakmilik.kodHakmilik.kod}${actionBean.hakmilik.noHakmilik}&nbsp;
            </p>
             <p>
                 <label>Kategori :</label>
                 ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
             </p>
            <p>
                <label>Tanah Rizab :</label>
                <c:if test="${actionBean.hakmilik.rizab.kod ne null}">${actionBean.hakmilik.rizab.kod}&nbsp;</c:if>
                <c:if test="${actionBean.hakmilik.rizab.kod eq null}"> Tidak </c:if>
                ${actionBean.hakmilik.rizab.kod}
            </p>
            <p>
                <label>Pihak Berkuasa Tempatan :</label>
                ${actionBean.hakmilik.pbt.kod}
            </p>
            <p>
                <label>Taraf Pegangan :</label>
                <c:if test="${actionBean.hakmilik.pegangan eq 'S'}">
                    Selama-lamanya
                </c:if>
                <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
                    Pajakan
                </c:if>
            </p>
            <p>
                <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
                    <label>Tempoh :</label>
                    ${actionBean.hakmilik.tempohPegangan}&nbsp;Tahun
                </c:if>
            </p>
            <p>
                <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
                    <label>Tarikh Luput :</label>
                    <c:if test="${actionBean.hakmilik.tarikhLuput eq null}">
                        Tiada Data
                    </c:if>
                    <s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhLuput}"/>&nbsp;
                </c:if>
            </p>
             <table align="center">
                <tr align="left" width="30%" valign="top">
                    <td>
                        <label>Syarat Nyata :</label>
                    </td>
                    <td>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}"> ${actionBean.hakmilik.syaratNyata.syarat} </c:if>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada Data </c:if>
                    </td>

                </tr>
                <tr align="left" width="30%" valign="top">
                    <td>
                        <label>Sekatan Kepentingan :</label>
                    </td>
                    <td>
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}"> ${actionBean.hakmilik.sekatanKepentingan.sekatan} </c:if>
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if>

                    </td>
                </tr>
            </table>--%>
            <p>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset>
    </div>
</s:form>