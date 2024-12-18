<%--
    Document   : hantarDMS
    Created on : sept 25, 2012, 3:08:04 PM
    Author     : zabedah.zainal
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function test(f) {
        $(f).clearForm();
    }

    function gisInbound(event, f,strIDPermohonan){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event+'&idPermohonan='+strIDPermohonan+'&idAliran=g_terima_pw';
        alert("url:"+url);
        $.post(url,q,
        function(data){
            $('#page_div',this.document).html(data);
        },'html');
    }

</script>
<s:form beanclass="etanah.view.stripes.pelupusan.GISIntegrationActionBean">

    <p align="center">

        <%--<c:if test="${edit2}">
            <s:button name="lakarPelan" id="lakarPelan" value="Semak PA/B1" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
        </c:if>--%>
        <c:if test="${edit3}">
            <%--<s:button name="lakarPelan" id="lakarPelan" value="Hantar PA/B1" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;--%>

            <s:button name="getInboundGIS" id="getInboundGIS" value="Gis Inbound" class="longbtn" onclick="gisInbound(this.name, this.form,'${actionBean.idPermohonan}');"/>
        </c:if>
    </p>

    <%-- <s:hidden name="idPermohonan"/>
     <div class="subtitle">
         <s:errors/>
         <s:messages/>
     </div>
     <div class="subtitle">
         <fieldset class="aras1">
             <legend>Integrasi Ke Jupem</legend>
             <p>
                 <label>ID Permohonan :</label>
                 ${actionBean.idPermohonan}
             </p><p>
                 <label>ID Aliran :</label>
                 ${actionBean.idAliran}
             </p>


            <br/>
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" name="getInboundGIS" value="Terima PA JUPEM" onclick="doSubmit(this.form, this.name,'page_div')"/>
            </p>

        </fieldset>--%>
    <%--<fieldset class="aras1">
               <legend>WorkList Integration</legend>
               <font size="1" color="Red">Sila masukkan Maklumat yang diperlukan.</font>
               <p>
                   <label><font color="red">*</font>ID Permohonan :</label>
                   <s:text name="idPermohonanL"/></p>
               <p>
                   <label><font color="red">*</font>Outcome :</label>
                   <s:text name="outCome"/></p>
               <p>
                    <label><font color="red">*</font>Pengguna :</label>
                   <s:text name="pengguna" />
               </p>

                   <label>&nbsp;</label>  <s:submit name="initiateTask" id="search" value="Terus" class="btn"/>


        </fieldset>--%>
    <%--</div>--%>
</s:form>
