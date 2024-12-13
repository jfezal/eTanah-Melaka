<%-- 
    Document   : kemasukan_juruukurberlesen
    Created on : Jul 15, 2010, 10:30:05 AM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
        function save(event, f){


            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');




    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form  name="form1" beanclass="etanah.view.strata.PaparanJuruukurActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Juruukur Berlesen</legend>
            <p>
                <label>Nama Syarikat : </label> <s:text name="mohonJUBL.jurukur.nama"  readonly="true" id="namaJUBL" size="30" />
            </p>
            <p>
                <label>Nama Juruukur Berlesen : </label><s:text name="mohonJUBL.namaJUBL"  readonly="true" id="namaJUBL" size="30" />
            </p>
            <p>
                <label>Tarikh :</label>
                <s:text name="mohonJUBL.tarikhRujukan" readonly="true" size="12" />
            </p>
            <p>
                <label> Nombor Rujukan :</label>
                <s:text name="mohonJUBL.noRujukan" size="30" id="noRujukan" readonly="true"/>
            </p>
            <p>
                <label> Keterangan :</label>
                <s:textarea name="mohonJUBL.ulasan" rows="5" cols="50"  id="ulasan" readonly="true" />
            </p>
            <p>
                <br>
                <label>&nbsp;</label>
            </p>
        </fieldset>
    </div>

</s:form>
