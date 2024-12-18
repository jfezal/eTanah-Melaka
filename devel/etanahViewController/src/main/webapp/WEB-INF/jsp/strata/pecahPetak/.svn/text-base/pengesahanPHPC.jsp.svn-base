<%--
    Document   : kemasukan_juruukurberlesen
    Created on : Jul 15, 2010, 10:30:05 AM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

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
<s:form  name="form1" beanclass="etanah.view.strata.PengesahanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
         <fieldset class="aras1">
            <legend>Persetujuan Perbadanan Pengurusan</legend>
            <p>
                <label>Nama : </label> <s:text name="badanUrus.pengurusanNama" size="30" id="noRujukan" /><em>*</em>
            </p>
            <p>
                <label>Tarikh :</label>
                <s:text name="badanUrus.idBadan" class="datepicker" size="12" /><em>*</em>
            </p>
            <p>
                <label> Nombor Rujukan :</label>
                <s:text name="badanUrus.idBadan" size="30" id="noRujukan" /><em>*</em>
            </p>
            <p>
                <label> Keterangan :</label>
                <s:textarea name="mohonJUBL.ulasan" rows="5" cols="50"  id="ulasan" /><em>*</em>
            </p>
        </fieldset>
        <fieldset class="aras1">
            <legend>Pengesahann Juruukur Berlesen</legend>
            <p>
                <label>Nama Syarikat : </label><s:select name="mohonJUBL.jurukur.idJubl"  id="idJubl">
                    <s:option value="mohonJUBL.jurukur">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiJUBL}" label="nama" value="idJubl"/>
                </s:select><em>*</em>
            </p>
            <p>
                <label>Nama Juruukur Berlesen : </label><s:text name="mohonJUBL.namaJUBL" id="namaJUBL" size="30" /><em>*</em>
            </p>
            <p>
                <label>Tarikh :</label>
                <s:text name="mohonJUBL.tarikhRujukan" class="datepicker" size="12" /><em>*</em>
            </p>
            <p>
                <label> Nombor Rujukan :</label>
                <s:text name="mohonJUBL.noRujukan" size="30" id="noRujukan" /><em>*</em>
            </p>
            <p>
                <label> Keterangan :</label>
                <s:textarea name="mohonJUBL.ulasan" rows="5" cols="50"  id="ulasan" /><em>*</em>
            </p>
            <p>
                <br>
                <label>&nbsp;</label>
                <c:if test="${actionBean.mohonJUBL eq null}">
                    <s:button name="simpanMaklumat" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                </c:if>
                <c:if test="${actionBean.mohonJUBL ne null}">
                    <s:button name="simpanMaklumat" id="simpan" value="Kemaskini" class="btn" onclick="save(this.name, this.form);"/>
                </c:if>
            </p>
        </fieldset>
    </div>

</s:form>
