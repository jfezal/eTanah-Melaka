<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/scripts/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/styles/datepicker.css"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<c:set var="nextStep" value="Step6"/>
<c:set var="prevStep" value="Step3"/>
<c:set var="word" value="Langkah 5"/>
<c:set var="action" value="/kaunter/permohonan"/>
<c:if test="${!empty carian}">    
    <c:set var="nextStep" value="Step3"/>
    <c:if test="${kodUrusan eq 'SSLSC'}">
        <c:set var="prevStep" value="Step2c"/>
        <c:set var="word" value="Langkah 4"/>
    </c:if>
    <c:if test="${kodUrusan ne 'SSLSC'}">
        <c:set var="prevStep" value="Step2"/>
        <c:set var="word" value="Langkah 3"/>
    </c:if>
    
    
    <c:set var="action" value="/daftar/carian"/>
</c:if>

<p class=title>${word}: Fi, Pengurangan Atau Tambahan (Jika Ada)</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan maklumat yang diperlukan di bawah jika berkenaan.</span><br/>

<s:form action="${action}" id="cajTamb">

        <c:set var="title" value="Hakmilik Terlibat"/>
        <c:if test="${perserahan}">
            <c:set var="title" value="Perserahan Terlibat"/>
        </c:if>

	<fieldset class="aras1">
	
	<display:table name="${actionBean.senaraiTransaksiSemasa}" id="row1" class="tablecloth" style="width:95%;" >
	
	    <display:column title="Bil">
	        ${row1_rowNum}
	    </display:column>
	    
	    <display:column title="Urusan" group="1" >
	       <b>${row1.utkUrusan.namaUrusan}</b>
	    </display:column>
	    
	    <display:column title="${title}" group="2">
                <c:forTokens delims="," items="${row1.senaraiHakmilik}" var="items">
                    ${items}<br/>
                </c:forTokens>
	    </display:column>
	
	    <display:column title="Transaksi" >
	       ${row1.namaUrusan}
	    </display:column>
	    
	
	    <display:column title="Kuantiti" style="width:100px;text-align:right;" >
	       ${row1.kuantiti}
	    </display:column>
	    
               <display:column property="amaun" title="Fi(RM)" style="width:100px;text-align:right;" format="{0,number, 0.00}"/>
	
	    <display:footer>
	        <tr>
	            <td colspan="5" align="left">Jumlah Perlu Dibayar (RM):</td>
                    <td><div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div></td>
	        </tr>
	    </display:footer>
	
	</display:table>
	
	</fieldset>

    <fieldset class="aras1">
        <legend>Pengurangan/Penambahan Fi</legend>

        <display:table name="${actionBean.senaraiUrusan}" id="row" class="tablecloth" >
            <display:column title="Urusan">
                <c:if test="${row != null && row.kodUrusan != null}">
                    ${row.kodUrusan} - ${row.namaUrusan}
                </c:if>
             </display:column>
            <display:column title="Pelepasan (RM)">
                    <s:text name="senaraiUrusan[${row_rowNum - 1}].cajPelepasan"  size="10" />
            </display:column>
            <display:column title="Pengecualian (RM)">
                    <s:text name="senaraiUrusan[${row_rowNum - 1}].cajPengecualian"  size="10" />
            </display:column>
            <display:column title="Notis (RM)">
                    <s:text name="senaraiUrusan[${row_rowNum - 1}].cajNotis" size="10" />
            </display:column>
            <display:column title="Fail (RM)">
                    <s:text name="senaraiUrusan[${row_rowNum - 1}].cajFail" size="10" />
            </display:column>
            <display:column title="Lain-lain (RM)">
                    <s:text name="senaraiUrusan[${row_rowNum - 1}].cajLain" size="10" />
            </display:column>
        </display:table>

        <br/> 
        <p align="center">
            
            <s:submit name="${prevStep}" value="Kembali" class="btn" />
            <s:submit name="CancelAll" value="Batal" class="btn" />
            <s:button  name="reset" value="Isi Semula" class="btn"  onclick="clearText('cajTamb');"/>
            <s:submit name="${nextStep}" value="Seterusnya" class="btn" />
        </p>

    </fieldset>

</s:form>