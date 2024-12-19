<%--
    Document   : dev_laporan_pelukis_pelan
    Created on : Nov 9, 2010, 11:45:34 AM
    Author     : nursyazwani
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
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
        text-align:left;
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
    function removeTanahRizab(idTanahRizabPermohonan)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_pelukisPelan?deleteTanahRizab&idTanahRizabPermohonan='
                +idTanahRizabPermohonan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                self.opener.refreshPageTanahRizab();
            },'html');
        }
    }

    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }
    <%--function RunProgram(strUserID, strPassword) {
               // alert("run");
                var objShell = new ActiveXObject("WScript.Shell");
                var sysVar = objShell.Environment("System");
                var strJawatan="rr";
                objShell.Run(sysVar("eTanahGISCharting") + " " + strUserID + " " + strPassword+ " " + strJawatan);
                self.close();
            }
    --%>
        function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
            alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
            strNama = ReplaceAll(strNama," ","_");
            strJawatan = ReplaceAll(strJawatan," ","_");
    <%--strStageID = ReplaceAll(strStageID," ","_");--%>
            //alert("Nama:" + strNama);
            //alert ("Jawatan:" + strJawatan);
            //alert ("StageId:" + strIDStage);
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strIDStage);
        }


        function removeTanahMilik(id)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_pelukisPelan?deleteTanahMilik&id='
                    +id;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }

        function removePermohonanTerdahulu(idPermohonan)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_pelukisPelan?deletePermohonanTerdahulu&idPermohonan='
                    +idPermohonan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');}
        }


        function tambahTanahRizab(){
            window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_pelukisPelan?tanahRizabPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function tambahTanahMilik(){
            window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_pelukisPelan?tanahMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function tambahPermohonanTerdahulu(){
            window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_pelukisPelan?permohonanTerdahuluPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function refreshPageTanahRizab(){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_pelukisPelan?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function popupTanahRizab(h){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_pelukisPelan?showEditTanahRizab&idTanahRizabPermohonan='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function popupPermohonanTerdahulu(h){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_pelukisPelan?showEditPermohonanTerdahulu&idPermohonan='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
    <%--function popup(i){

     var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_tambahan?showeditTanahRizab&idTanahRizabPermohonan="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");


}--%>
    $(document).ready( function() {            
        $('.hakmilikSebelum').click( function() {
            //                alert($(this).text());
            window.open("${pageContext.request.contextPath}/penguatkuasaan/laporan_pelukisPelan?permohonanSebelumDetail", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
        });
    });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.penguatkuasaan.LaporanPelukisPelanActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="kodD" id="kodD"/>
    <div class="subtitle displaytag">
        <fieldset class="aras1" id="locate">
            <legend>
                Tanah Milik 
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="hakmilik.kodHakmilik.nama" title="Jenis Milik" />
                    <display:column property="hakmilik.cawangan.name" title="Cawangan" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" />
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column property="hakmilik.lot.nama" title="Kod Lot"/>
                    <%--<display:column property="hakmilik.noLot" title="No. PT/Lot"/>--%>
                    <display:column title="No. PT/Lot" value = "${actionBean.noLot}"/>
                    <display:column property="hakmilik.idHakmilik" title="No. H/M"/>
                    <display:column title="Kawasan PBT">
                        <c:if test="${line.hakmilik.pbt ne null}">${line.hakmilik.pbt.nama}</c:if>
                        <c:if test="${line.hakmilik.pbt eq null}">Luar Kawasan PBT</c:if>
                    </display:column>
                    <c:if test="${edit}">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPCB'}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeTanahMilik('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                                </div>
                            </display:column>
                        </c:if>
                    </c:if>
                </display:table>
                <c:if test="${edit}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPCB'}">
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahMilik();"/>&nbsp;
                    </c:if>
                </c:if>
                <br>
            </div>
        </fieldset>
    </div>
    <br/>
    <br/>
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Tanah Rizab
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.tanahRizabList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/laporan_pelukisPelan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column property="cawangan.name" title="Cawangan" />
                    <display:column property="daerah.nama" title="Daerah" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column property="noLot" title="No. PT/Lot"/>
                    <display:column property="noWarta" title="No. Warta"/>
                    <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
                    <%-- <display:column property="catatan" title="Catatan"/>--%>
                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupTanahRizab('${line.idTanahRizabPermohonan}');"/>
                            </div>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeTanahRizab('${line.idTanahRizabPermohonan}');" />
                            </div>
                        </display:column>
                    </c:if>
                </display:table>
                <c:if test="${edit}">
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahRizab();"/>&nbsp;
                </c:if>
                <br>
            </div>
        </fieldset>
    </div>

    <br/>
    <br/>
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Permohonan Terdahulu
            </legend>
            <div class="content" align="center">
                <c:if test="${actionBean.permohonan.permohonanSebelum ne null}">                
                    <display:table class="tablecloth" name="${actionBean.permohonan}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No">${line_rowNum}</display:column>
                        <display:column property="permohonanSebelum.idPermohonan" title="ID Permohanan/No Fail" class="popup hakmilikSebelum" style="vertical-align:baseline"/>
                    </display:table>
                </c:if>
                <c:if test="${actionBean.permohonan.permohonanSebelum eq null}">                    
                    <table class="tablecloth" align="center">
                        <tr>
                            <th> No </th>
                            <th>ID Permohanan/No Fail</th>
                            <th>Kemaskini </th>
                            <th>Hapus</th>
                        </tr>
                    </table>
                </c:if><br/>
                <br>
            </div>
        </fieldset>
    </div>
    <br/>
    <br/>
    <div class="content" align="center">
        <c:if test="${edit}">
            <table width="52%" border="0">
                <tr>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Lembaran Piawai</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${actionBean.noLitho}</font></td>
                </tr>
                <tr>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jenis Tanah</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                            <s:select name="kodT" style="width:150px" value="" id="kodTanah" onchange="javaScript:showjenistanahlain(this.value)">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodTanah}" label="nama" value="kod"/>
                            </s:select>
                        </font></td>
                </tr>
                <tr>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Warta</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><s:text name="noWartaTanah" size="20" id="noWartaTanah" class="normal_text"/></font></td>
                </tr>
                <tr>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Di tanda untuk projek kerajaan</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><s:text name="projekKerajaan" size="20" id="projekkerajaan" class="normal_text"/></font></td>
                </tr>
                <tr>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Lain-lain hal</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><s:textarea name="catatan" rows="5" cols="50" id="catatan" class="normal_text"/></font></td>
                </tr>
            </table>
            <p align="center">
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <%-- <s:button name="Charting" id="btnClick" value="Charting" class="btn" onclick="RunProgram('ppelan1','etanah123')"/>--%>
                <s:button name="lakarPelan" id="lakarPelan" value="Charting" class="btn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
            </p>

        </c:if>
        <c:if test="${!edit}">
            <table width="52%" border="0" align="center" cellspacing="8">
                <tr>
                    <td width="35%" valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Lembaran Piawai</font></td>
                    <td  width="2%" valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                    <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;" >${actionBean.noLitho} &nbsp;</font></td>
                </tr>
                <tr>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jenis Tanah</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">
                            <s:select name="kodT" style="width:150px" value="" id="kodTanah" onchange="javaScript:showjenistanahlain(this.value)">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodTanah}" label="nama" value="kod"/>
                            </s:select>
                        </font></td>
                </tr>
                <tr>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Warta</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                    <td><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;"><s:text name="noWartaTanah" size="20" id="noWartaTanah" class="normal_text"/></font></td>
                </tr>
                <tr>
                    <td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Di tanda untuk projek kerajaan</font></td>
                    <td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                    <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${actionBean.projekKerajaan} &nbsp;</font></td>
                </tr>
                <tr>
                    <td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Catitan Pelukis Pelan</font></td>
                    <td valign="top"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>
                    <td valign="top"><font style="font-size:13px; color:black; font-weight:normal; font-family:Tahoma;">${actionBean.catatan} &nbsp;</font></td>
                </tr>
            </table>
        </c:if>
    </div>
    <%--<input type="button" id="btnClick" value="Charting" onclick="RunProgram('ppelan1','etanah123')" />--%>


</s:form>