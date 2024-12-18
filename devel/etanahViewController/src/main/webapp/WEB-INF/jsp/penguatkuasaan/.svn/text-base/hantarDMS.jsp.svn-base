<%--
    Document   : hantarDMS
    Created on : March 17, 2011, 5:53:04 PM
    Author     : sitifariza.hanim
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function test(f) {
        $(f).clearForm();
    }

</script>
<s:form beanclass="etanah.view.penguatkuasaan.HantarGISFailKeDMSActionBean">

    <%--<s:hidden name="mohon.idPermohonan"/>--%>
    <s:hidden name="idPermohonan"/>
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Integrasi Ke Jupem</legend>
            <%--<font size="1" color="Red">Sila masukkan ID Permohonan dan ID Aliran.</font>--%>
            <p>
                <label>ID Permohonan :</label>
                ${actionBean.idPermohonan}
                <%--<s:text name="idPermohonan" size="30" readonly="true"/>--%>
            </p><p>
                <label>ID Aliran :</label>
                ${actionBean.idAliran}
                <%--<s:text name="idAliran" size="20" readonly="true"/>--%>
            </p>


                <%--<s:submit name="getInboundGIS" id="search" value="Terus" class="btn"/>--%>
                <br/>
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" name="getInboundGIS" value="Terus" onclick="doSubmit(this.form, this.name,'page_div')"/>
            </p>

        </fieldset>
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
    </div>
</s:form>
