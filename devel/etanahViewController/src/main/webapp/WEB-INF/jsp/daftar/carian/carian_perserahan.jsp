<%--
    Document   : carian
    Created on : Dec 23, 2009, 10:13:28 AM
    Author     : fikri
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
function test(f) {
        $(f).clearForm();
    }
</script>


<p class=title>Langkah 3: Tentukan Hakmilik-Hakmilik Terlibat2</p>

<s:messages/>
<s:errors/>
<span class=instr>Sila masukan maklumat hakmilik dan cari. Seterusnya sila pilih maklumat yang berkenaan.</span><br/>
<s:form beanclass="etanah.view.daftar.CarianActionBean">
    <s:hidden name="flag" value="perserahan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Hakmilik
            </legend>
            <p>
                <label>Urusan :</label>
                <s:select name="permohonan.kodUrusan.kod" style="width:400px">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodUrusanPerserahan}" value="kod" label="nama"/>
                </s:select>
            </p>
            <p>
                <label>ID Perserahan :</label>
                <s:text name="permohonan.idPermohonan" />
            </p>            
            <br/>
            <p>
                <label>&nbsp;</label>
                <s:submit name="searchPerserahan" value="Cari" class="btn"/>
                <s:button  name="reset" value="Isi Semula" class="btn" onclick="test(this.form);"/>
            </p>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Carian
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.permohonanList}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/daftar/senarai_tugas" id="line">
                    <display:column>
                        <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.idPermohonan}"/>
                    </display:column>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idPermohonan" title="ID Perserahan"/>
                    <display:column property="kodUrusan.nama" title="Urusan"/>                    
                </display:table>
            </div>
             <p><label>&nbsp;</label>
	            <s:submit name="Step2" value="Kembali" class="btn" />
	            <s:submit name="CancelAll" value="Batal" class="btn" />
	            <s:submit name="Step4" value="Seterusnya" class="btn" />
	        </p>
        </fieldset>

    </div>
</s:form>
