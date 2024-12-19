<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/scripts/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/styles/datepicker.css"></script>
<p class=title>Langkah 4: Maklumat Tambahan Urusan</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan maklumat yang diperlukan di bawah. Medan bertanda <em>*</em> adalah mandatori.</span><br/>

<s:form action="/pelupusan/kaunter" id="urusanInfoTamb">

    <fieldset class="aras1">
        <legend>Maklumat Tambahan Urusan</legend>

        <display:table name="${actionBean.senaraiUrusan}" id="row" class="tablecloth" >
            <display:column title="Urusan">
                <c:if test="${row != null && row.kodUrusan != null}">
                    ${row.kodUrusan}
                </c:if>
             </display:column>
            <display:column>
                <c:if test="${row.labelAmaun1 != null}">
                    ${row.labelAmaun1}:<s:text name="senaraiUrusan[${row_rowNum - 1}].amaun1"/>
                </c:if>
            </display:column>
            <display:column>
                <c:if test="${row.labelTarikh1 != null}">
                    ${row.labelTarikh1}:<s:text name="senaraiUrusan[${row_rowNum - 1}].tarikh1"/>
                </c:if>
            </display:column>
            <display:column>
                <c:if test="${row.labelNilai1 != null}">
                    ${row.labelNilai1}:<s:radio name="senaraiUrusan[${row_rowNum - 1}].nilai1" value="Y" />Ya
                    <s:radio name="senaraiUrusan[${row_rowNum - 1}].nilai1" value="T" />Tidak
                </c:if>
            </display:column>
        </display:table>

        <br/> 
        <p align="center">
            <s:submit name="CancelAll" value="Batal" class="btn" />
            <s:submit name="Step3" value="Kembali" class="btn" />
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="test(this.form);"/>            
            <s:submit name="Step5" value="Seterusnya" class="btn" />
        </p>

    </fieldset>

</s:form>