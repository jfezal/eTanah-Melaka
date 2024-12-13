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


<p class=title>Langkah 3: Tentukan Hakmilik-Hakmilik Terlibat</p>

<s:messages/>
<s:errors/>
<span class=instr>Sila masukan maklumat hakmilik dan cari. Seterusnya sila pilih maklumat yang berkenaan.</span><br/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.daftar.CarianActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian Hakmilik
            </legend>
            <p>
                <label>ID Hakmilik :</label>
                <s:text name="hakmilik.idHakmilik"/>
            </p>
            <p>
                <label>Daerah :</label>
                <s:select name="hakmilik.daerah.kod" style="width:400px">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodDaerah}" value="kod" label="nama"/>                    
                </s:select>
            </p>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <s:select name="hakmilik.bandarPekanMukim.kod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiBandarPekanMukim}" value="kod" label="nama"/>
                </s:select>
            </p>
            <p>
                <label>Lot :</label>
                <s:select name="hakmilik.lot.kod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodLot}" value="kod" label="nama"/>
                </s:select>
            </p>
             <p>
                <label>No Lot :</label>
                <s:text name="noLotDari"/> Hingga <s:text name="noLotHingga"/>
            </p>
            <br/>
            <p>
                <label>&nbsp;</label>
                <s:submit name="searchHakmilik" value="Cari" class="btn"/>
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
                <display:table class="tablecloth" name="${actionBean.hakmilikList}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/daftar/carian" id="line">
                    <display:column>
                        <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.idHakmilik}"/>
                    </display:column>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idHakmilik" title="ID Hakmilik"/>
                    <display:column property="daerah.nama" title="Daerah"/>
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"/>
                    <display:column property="noLot" title="No Lot"/>
                    <display:column property="lot.nama" title="Lot"/>
                    <display:column property="kodStatusHakmilik.nama" title="Status Hakmilik" sortable="true" sortName="kodStatusHakmilik.kod"/>
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
