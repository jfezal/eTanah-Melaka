<%-- 
    Document   : utility_qtft
    Created on : Mar 25, 2015, 11:07:51 AM
    Author     : haqqiem
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript">
    $(document).ready(function() {
//        $('#search').attr("disabled", "true");
        $('#process').attr("disabled", "true");
        var l = ${fn:length(actionBean.senaraiAkaun)};
        if(l == 0){
            $('#param').attr("style", "display:none;");
            $('#details').attr("style", "display:none;");
        }
    });
    
    function checkingIDHakmilik(id){
        if(id.length>0){
            $.get("${pageContext.request.contextPath}/hasil/qtft?doCheckHakmilik&idHakmilik=" + id,
            function(data){
                if(data == '0'){
                    alert('ID Hakmilik yang dimasukkan tidak wujud. Sila masukkan semula');
//                    $('#idhm').val('');
//                    $('#search').attr("disabled", "true");
                }
                if(data == '1'){
                    alert('ID Hakmilik yang dimasukkan masih AKTIF. Sila masukkan semula');
//                    $('#idhm').val('');
//                    $('#search').attr("disabled", "true");
                }
                if(data == '3'){
//                    alert('Tidak terdapat Hakmilik sambungan bagi ID Hakmilik yang dimasukkan. Sila masukkan semula');
//                    $('#idhm').val('');
//                    $('#search').attr("disabled", "true");
                }
                if(data == '4'){
                    $('#search').removeAttr("disabled");
                }
            });
        }else{
            $('#search').attr("disabled", "true");
        }
    }
    
    function checkTunggakan(noAkaun){
        $.get("${pageContext.request.contextPath}/hasil/qtft?doCheckTunggakan&noAkaun=" + noAkaun,
            function(data){
                if(data == 1){
                    alert("Tunggakan QT telah dibawa untuk Akaun "+noAkaun);
                    $('#process').attr("disabled", "true");
                }else{
                    $('#process').removeAttr("disabled");
                }                    
            });
    }
</script>

<div align="center">
    <table style="width:99.2%" bgcolor="green">
        <tr><td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Utility QT-FT</font>
                </div>
            </td>
        </tr>
    </table>
</div>
<s:form beanclass="etanah.view.stripes.hasil.UtilityQTFTActionBean" id="qt" name="form">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>ID Hakmilik Sebelum</legend>
            <p class=instr><em><font color="black">Masukkan ID Hakmilik.<br>&nbsp;
                <font color="red">PERINGATAN:</font>ID Hakmilik yang dimasukkan mestilah ID Hakmilik yang berstatus Batal.</font></em>
            </p>
            <p>
                <label>ID Hakmilik :</label>
                <s:text name="hakmilikSebelum.hakmilikSebelum" id="idhm" size="20" 
                        onkeyup="this.value=this.value.toUpperCase();" maxlength="17"
                        onblur="checkingIDHakmilik(this.value);"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="Step2" class='btn' value="Cari" id='search'/>
                <s:button name="" class='btn' value="Isi Semula" onclick="clearText('qt');"/>&nbsp;
            </p><br>
            
            <div id="details">
                <p>
                    <label>Nombor Akaun :</label>
                    ${actionBean.akaun.noAkaun}&nbsp;
                </p>
                <p>
                    <label>Baki (RM) :</label>
                    <fmt:formatNumber value="${actionBean.akaun.baki}" pattern="#,###,##0.00"/>&nbsp;
                </p>
                <p>
                    <label>Tarikh Daftar :</label>
                    <fmt:formatDate value="${actionBean.akaun.hakmilik.tarikhDaftar}" pattern="dd/MM/yyyy"/>&nbsp;
                </p>
                <p>
                    <label>Tarikh Batal :</label>
                    <fmt:formatDate value="${actionBean.akaun.hakmilik.tarikhBatal}" pattern="dd/MM/yyyy HH:mm:ss a"/>&nbsp;
                </p>
            </div>
        </fieldset>
    </div><br>
    <div class="subtitle" id="param">
        <fieldset class="aras1">
            <legend>Hasil Carian</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiAkaun}" requestURI="/hasil/qtft" id="line">
                    <display:column title="Pilih" >
                        <div align="center"><s:radio name="rbtAkaun" value="${line.noAkaun}" onclick="checkTunggakan(this.value)" id="rbt"/></div>
                    </display:column>
                    <display:column title="Nombor Akaun" property="noAkaun" />
                    <display:column title="ID Hakmilik" property="hakmilik.idHakmilik" />
                    <display:column title="Daerah" property="hakmilik.daerah.nama" />
                    <display:column title="Bandar/Pekan/Mukim" property="hakmilik.bandarPekanMukim.nama" />
                    <display:column title="Jenis Hakmilik">${line.hakmilik.kodHakmilik.kod} - ${line.hakmilik.kodHakmilik.nama}</display:column>
                    <display:column title="Tarikh Daftar" style="text-align:center">
                        <fmt:formatDate value="${line.hakmilik.tarikhDaftar}" pattern="dd/MM/yyyy hh:MM:ss a"/>
                    </display:column>
                    <display:column title="Status Hakmilik" property="hakmilik.kodStatusHakmilik.nama"/>
                    <display:column title="Status Akaun" property="status.nama"/>
                    <display:column property="baki" title="Jumlah Perlu Bayar (RM)" class="${line_rowNum}" format="{0,number, #,###,##0.00}" style="width:100;text-align:right;"/>
                </display:table>
            </div>
        </fieldset>
        <div align="center"><table style="width:100%" bgcolor="green">
        <tr>
            <td align="right">
                <s:submit name="Step3" value="Process" class="btn" id="process"/>
            </td>
        </tr>
    </table></div>
    </div>
</s:form>
