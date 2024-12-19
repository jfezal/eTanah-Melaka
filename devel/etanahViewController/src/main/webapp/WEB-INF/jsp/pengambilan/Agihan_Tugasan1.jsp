<%-- 
    Document   : Agihan_Tugasan1
    Created on : 08-Jun-2011, 01:19:27
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function doAgih(e, f) {
        var i = $('#id_pguna').val();
            if(i == ''){
                alert('Sila pilih pengguna terlebih dahulu.');
                $('#id_pguna').focus();
                return false;
            }
        if(confirm('Adakah anda pasti? Sila semak dokumen terlebih dahulu jika belum semak.')) {
            $.blockUI({
                message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&' + q;
            f.submit();
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

    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        var stageId = "g_terima_pa_b1";
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
        objShell.Run(sysVar("eTanahGISB1") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }

    function gisInbound(event, f,strIDPermohonan){
        <%--alert(strIDPermohonan);--%>
       var q = $(f).formSerialize();
       var url = f.action + '?' + event+'&idPermohonan='+strIDPermohonan+'&idAliran=g_terima_pa_b1';
       alert("url:"+url);
        <%--var url = f.action + '?' + event+"&idPermohonan='+0401DEV2011000012&idAliran=g_terima_pa_b1";  --%>
        $.post(url,q,
        function(data){
            $('#page_div',this.document).html(data);
        },'html');
    }

</script>
<s:useActionBean beanclass="etanah.view.stripes.pengambilan.agihan_tugasanB1" var="penggunaperanan"/>
<stripes:form beanclass="etanah.view.stripes.pengambilan.agihan_tugasanB1">
    <s:messages/>
    <div align="center">
        <c:if test="${edit1}">
            <s:button name="lakarPelan" id="lakarPelan" value="Terima & Agih PA/B1" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
        </c:if>
    </div>
    
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Fail
            </legend>
            <p>
                <label for="ID Berkaitan"> ID Perserahan : </label>
                ${actionBean.mohon.idPermohonan}&nbsp;
            </p>
            <p><label for="Urusan">Urusan : </label>
                ${actionBean.mohon.kodUrusan.nama}&nbsp;
            </p>
        </fieldset>
        <br/>
    </div>
  
            <%--pelukispelan--%>
    <c:if test="${penggunaperanan.stage eq 'agihantugas1'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Agihan Tugas
                </legend>
                <p><label>Hantar Kepada :</label>
                    <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna">
                        <s:option value="">-- Sila Pilih --</s:option>
                        <s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="jawatan" />
                    </s:select>
                </p>
                <br/>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="agihPT" value="Agih" class="btn" onclick="doAgih(this.name, this.form);"/>
                </p>
            </fieldset>
        </div>
    </c:if>
    <%--penolongpegawaitanah--%>
    <c:if test="${penggunaperanan.stage eq 'agihantugas3'}">
       <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Agihan Tugas
            </legend>
            <p><label>Hantar Kepada :</label>
                <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="nama" />
                </s:select>
            </p>
            <br/>
            <p>
                <label>&nbsp;</label>
                 <s:button name="agihPT" value="Agih" class="btn" onclick="doAgih(this.name, this.form);"/>
            </p>
        </fieldset>
    </div>
            </c:if>
    
    <%--penolongpentadbir/pembantutadbirteringgi--%>
    <c:if test="${penggunaperanan.stage eq 'agihantugas3'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Agihan Tugas
                </legend>
                <p><label>Hantar Kepada :</label>
                    <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna">
                        <s:option value="">-- Sila Pilih --</s:option>
                        <s:options-collection collection="${penggunaperanan.listPp3}" value="idPengguna" label="jawatan" />
                    </s:select>
                </p>
                <br/>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="agihPT" value="Agih" class="btn" onclick="doAgih(this.name, this.form);"/>
                </p>
            </fieldset>
        </div>
    </c:if>
    <%--penolong pegawai tanah ptg--%>
    <c:if test="${penggunaperanan.stage eq 'agihantugas4'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Agihan Tugas
                </legend>
                <p><label>Hantar Kepada :</label>
                    <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna">
                        <s:option value="">-- Sila Pilih --</s:option>
                        <s:options-collection collection="${penggunaperanan.listPp4}" value="idPengguna" label="jawatan" />
                    </s:select>
                </p>
                <br/>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="agihPT" value="Agih" class="btn" onclick="doAgih(this.name, this.form);"/>
                </p>
            </fieldset>
        </div>
    </c:if>
    <%--pembantu tadbir ptg--%>
    <c:if test="${penggunaperanan.stage eq 'agihantugas5'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Agihan Tugas
                </legend>
                <p><label>Hantar Kepada :</label>
                    <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna">
                        <s:option value="">-- Sila Pilih --</s:option>
                        <s:options-collection collection="${penggunaperanan.listPp5}" value="idPengguna" label="jawatan" />
                    </s:select>
                </p>
                <br/>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="agihPT" value="Agih" class="btn" onclick="doAgih(this.name, this.form);"/> &nbsp;&nbsp;&nbsp;
                </p>
            </fieldset>
        </div>
    </c:if>
    <br><br>
    <p align="center">

        
            <s:button name="lakarPelan" id="lakarPelan" value="Semak PA/B1" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
       
       <c:if test="${edit3}">
            <s:button name="lakarPelan" id="lakarPelan" value="Hantar PA/B1" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;

            <s:button name="getInboundGIS" id="getInboundGIS" value="Gis Inbound" class="longbtn" onclick="gisInbound(this.name, this.form,'${actionBean.idPermohonan}');"/>
       </c:if>
    </p>

</stripes:form>

