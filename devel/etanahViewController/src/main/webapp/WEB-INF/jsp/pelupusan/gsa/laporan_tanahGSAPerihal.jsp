<%--
    Document   :  laporan_tanahGSAPerihal.jsp
    Created on :  Jan 03, 2012, 10:18:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PERIHAL TANAH</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
    
    $(document).ready(function() {
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    <c:if test="${actionBean.laporanTanah.bolehBerimilik ne null}">
        <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'T'}">
                $('#jikasebab').show();
        </c:if>
        <c:if test="${actionBean.laporanTanah.bolehBerimilik eq 'Y'}">
                $('#jikasebab').hide();
        </c:if>
    </c:if>
    <c:if test="${actionBean.laporanTanah.bolehBerimilik eq null}">
            $('#jikasebab').hide();
    </c:if>
    <c:if test="${actionBean.permohonanLaporanPelan.kodTanah.kod eq '99'}">
            $('#jenistanahlainlain').show();
    </c:if>
        
        }); //END OF READY FUNCTION
         
        function showSebab() {
            $('#jikasebab').show();
        }

        function hideSebab() {
            $('#jikasebab').hide();
        }

        function showjenistanahlain(value){
            // alert(value);
            if(value == '99'){
                $('#jenistanahlainlain').show();
            }else{
                $('#jenistanahlainlain').hide();
            }
        }
    
        function refreshpage(type){
            //        alert(type);
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?refreshpage&type='+type;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
    
        function refreshpage(){
            //        alert('aa');
            NoPrompt();
            opener.refreshGSA('main');
            self.close();
        }
    
   
</script>
<body>
    <script>
        // Allow the user to be warned by default.
                var allowPrompt = true;
                
                window.onbeforeunload = WarnUser;
                function WarnUser()
                {   
                    if(allowPrompt)
                        refreshpage();
                    if(allowPrompt)
                    {
                        event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
                    }
                    else
                    {
                        // Reset the flag to its default value.
                        allowPrompt = true;
                    }
                }
                function NoPrompt()
                {
                    allowPrompt = false;
                }

    </script>
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahGSAPelupusanActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik"/>
        <div class="subtitle">
            <fieldset class="aras1">

                <div id="perihaltanah">
                    <legend>Perihal Tanah</legend>
                    <div class="content" align="center">
                        Tanah Kerajaan
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/pelupusan/laporan_tanahGSA">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.hakmilikPermohonan.id}"/></display:column>
                            <display:column title="ID Hakmilik" >${line.hakmilikPermohonan.hakmilik.idHakmilik}</display:column>    
                            <display:column title="No.Lot/PT" >${line.hakmilikPermohonan.noLot}</display:column>
                            <display:column title="Bandar/Pekan/Mukim" property="hakmilikPermohonan.bandarPekanMukimBaru.nama"/>
                            <display:column title="Seksyen" property="hakmilikPermohonan.kodSeksyen.nama" /><%----%><%--<s:select name="kodSeksyen.nama" value="${actionBean.hakmilikPermohonan.kodSeksyen.kod}">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.kodSeksyenList}" label="nama" value="kod" /></s:select></display:column>--%>
                            <display:column title="Daerah" property="hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama"/>
                            <display:column title="Luas Dipohon"><s:format formatPattern="#,###,##0.0000" value="${line.hakmilikPermohonan.luasTerlibat}"/> ${line.hakmilikPermohonan.kodUnitLuas.nama} </display:column>
                        </display:table>

                        <br>
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>
                                    Status Tanah :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.kodMilik.nama}&nbsp;
                                </td>
                            </tr>
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne null}">
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
                                    <tr>
                                        <td>
                                            Tanah Kerajaan Boleh diberimilik :
                                        </td>
                                        <td>
                                            <s:radio name="laporanTanah.bolehBerimilik" value="Y" onclick="hideSebab();" />&nbsp;Ya
                                            <s:radio name="laporanTanah.bolehBerimilik" value="T" onclick="showSebab();"/>&nbsp;Tidak
                                        </td>
                                    </tr>
                                    <tr id="jikasebab">
                                        <td>
                                            Jika Tidak Boleh diberimilik, sebab :
                                        </td>
                                        <td>
                                            <s:textarea name="laporanTanah.sebabTidakBolehBerimilik" rows="5" cols="50"/>
                                        </td>
                                    </tr>                                                
                                </c:if>
                            </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne null}">
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                                    <tr>
                                        <td colspan="2">                       

                                            <display:table  name="${actionBean.permohonan.senaraiHakmilik}" id="line" class="tablecloth">
                                                <display:column title="ID Hakmilik">
                                                    <c:if test="${line.hakmilik.kodHakmilik.nama ne null}"> ${line.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                                                    <c:if test="${line.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                                                </display:column>

                                                <display:column title="Jenis Hakmilik">
                                                    <c:if test="${line.hakmilik.idHakmilik ne null}"> ${line.hakmilik.idHakmilik}&nbsp; </c:if>
                                                    <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                                                </display:column>

                                                <display:column title="No Lot" >
                                                    <c:if test="${line.hakmilik.noLot ne null}"> ${line.hakmilik.noLot}&nbsp; </c:if>
                                                    <c:if test="${line.hakmilik.noLot eq null}"> Tiada Data </c:if>

                                                </display:column>

                                                <display:column title="Luas">
                                                    <c:if test="${line.hakmilik.luas ne null}"> <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</c:if>
                                                    <c:if test="${line.hakmilik.luas eq null}"> Tiada Data </c:if>
                                                </display:column>

                                                <display:column property="hakmilik.kategoriTanah.nama" title="Kegunaan" >
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                                                </display:column>

                                                <display:column title="Cukai (RM)">
                                                    <c:if test="${line.hakmilik.cukai ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.cukai}"/>&nbsp; </c:if>
                                                    <c:if test="${line.hakmilik.cukai eq null}"> Tiada Data </c:if>
                                                </display:column>
                                            </display:table>      
                                        </td>
                                    </tr>
                                </c:if>
                            </c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.kodMilik ne null}">
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'R'}">
                                    <tr>
                                        <td>
                                            Jenis Rizab :
                                        </td>
                                        <td>
                                            <s:select name="tanahR" style="width:250px" id="tanahR" >
                                                <s:option value="0">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod"/>
                                            </s:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            No. Warta Kerajaan :
                                        </td>
                                        <td>
                                            <s:text name="tanahrizabpermohonan1.noWarta" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Jenis Tanah :
                                        </td>
                                        <td>
                                            <s:select name="kodT" style="width:150px" value="" id="kodTanah" onchange="javaScript:showjenistanahlain(this.value)">
                                                <s:option value="0">Sila Pilih</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodTanah}" label="nama" value="kod"/>
                                            </s:select>
                                        </td>
                                    </tr>
                                    <div id="jenistanahlainlain">
                                        <tr>
                                            <td>Lain-lain :
                                            </td>
                                            <td>
                                                <s:textarea name="kand" rows="5" cols="50"/>
                                            </td>
                                        </tr>
                                    </div>
                                </c:if>                            
                            </c:if>
                            <c:if test="${actionBean.kodNegeri eq '04'}">
                                <tr>
                                    <td>
                                        Kawasan Parlimen :
                                    </td>
                                    <td>
                                        ${actionBean.hakmilikPermohonan.kodDUN.nama}&nbsp;
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        DUN :
                                    </td>
                                    <td>
                                        ${actionBean.hakmilikPermohonan.kodKawasanParlimen.nama}&nbsp;
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>
                                    Kedudukan Tanah :
                                </td>
                                <td>
                                    <s:textarea name="hakmilikPermohonan.lokasi" rows="5" cols="70" class="normal_text"/>
                                </td>
                            </tr>   
                        </table>
                        <br>
                        <legend align="left">
                            Permohonan Terdahulu
                        </legend>
                        <display:table class="tablecloth" name="${actionBean.permohonanManualList}" pagesize="20" cellpadding="0" cellspacing="0" id="line1">
                            <s:hidden name="" class="${line1_rowNum -1}" value="${line1.idMohonManual}"/>
                            <display:column title="No">${line1_rowNum}</display:column>
                            <display:column title="ID Permohanan/No Fail"  property="idPermohonan.idPermohonan"/>
                            <display:column title="Urusan" property="idPermohonan.kodUrusan.nama"/>
                            <display:column title="Status Keputusan" property="idPermohonan.keputusan.nama" />
                            <c:if test="${actionBean.display eq 'false'}">
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line1_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanTerdahulu('${line1.idMohonManual}')"/>
                                    </div>
                                </display:column></c:if>
                        </display:table>
                    </div>
                    <br/>
                </div>
            </fieldset>
        </div>
        <fieldset class="aras1">
            <table  width="100%" border="0">
                <tr>
                    <td align="center">
                        <s:submit name="simpanPerihal" value="Simpan" class="btn" onclick="NoPrompt();"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>   
        </fieldset>
    </s:form>
</body>

