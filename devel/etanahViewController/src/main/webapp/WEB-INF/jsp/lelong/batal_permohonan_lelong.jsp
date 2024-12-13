<%-- 
    Document   : batal_permohonan_lelong
    Created on : Oct 15, 2010, 4:20:53 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">


    function remove(val){
        if(confirm("Adakah Anda Pasti?")){
            var url = '${pageContext.request.contextPath}/lelong/permohonan_batal?delete&idMohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }


</script>

<s:form beanclass="etanah.view.stripes.lelong.PermohonanBatalActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle displaytag">
        <c:if test="${actionBean.boton ne true}">
            <fieldset class="aras1">
                <legend>
                    Senarai Urusan Terlibat
                </legend>

                <div class="subtitle" align="center">
                    <display:table class="tablecloth" name="${actionBean.listHP2}" id="line" pagesize="10"  requestURI="/lelong/permohonan_batal">
                        <c:if test="${actionBean.boton ne true}">
                            <display:column>
                                <s:radio name="idsblm" id="idsblm${line_rowNum - 1}" value="${line.idPermohonan}" class="checkbox"/>
                            </display:column>
                        </c:if>
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column property="idPermohonan" title="ID Perserahan"/>
                        <display:column title="ID Hakmilik">
                            <c:forEach items="${line.senaraiHakmilik}" var="i">
                                ${i.hakmilik.idHakmilik}<br>
                            </c:forEach>
                        </display:column>
                        <display:column title="ID Sebelum">
                            <c:if test="${line.permohonanSebelum == null}">
                                &nbsp; -
                            </c:if>
                            <c:if test="${line.permohonanSebelum != null}">
                                ${line.permohonanSebelum.idPermohonan}
                            </c:if>
                        </display:column>
                        <display:column property="kodUrusan.nama" title="Urusan"/>
                    </display:table>
                </div><br>

                <c:if test="${fn:length(actionBean.listHP2) > 0}">
                    <p class=instr align="center">
                        *<em>Petunjuk : Sila Pilih Satu Sahaja</em>
                    </p><br>
                    <div align="center">
                        <s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </div>
                </c:if>
                <br>
            </fieldset>

            <%--Edit to fit Nota Batal/lulus/--%>
            <fieldset class="aras1">
                <legend>
                    Urusan Yang Disimpan
                </legend>

                <div class="subtitle" align="center">
                    <display:table class="tablecloth" name="${actionBean.listMohon}" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column property="permohonanSebelum.kodUrusan.nama" title="Urusan"/>
                        <display:column property="permohonanSebelum.idPermohonan" title="ID Perserahan"/>
                        <display:column title="ID Hakmilik">
                            <c:forEach items="${line.permohonanSebelum.senaraiHakmilik}" var="i">
                                ${i.hakmilik.idHakmilik}<br>
                            </c:forEach>
                        </display:column>
                        <c:if test="${actionBean.boton ne true}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="remove('${line.permohonanSebelum.idPermohonan}')" onmouseover="this.style.cursor='pointer';">
                                </div>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>
            </fieldset>
        </c:if>
        <c:if test="${actionBean.boton eq true}">
            <fieldset class="aras1">
                <legend>
                    Urusan Yang Terlibat
                </legend>

                <div class="subtitle" align="center">
                    <display:table class="tablecloth" name="${actionBean.listMohon}" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column property="permohonanSebelum.kodUrusan.nama" title="Urusan"/>
                        <display:column property="permohonanSebelum.idPermohonan" title="ID Perserahan"/>
                        <display:column title="ID Hakmilik">
                            <c:forEach items="${line.permohonanSebelum.senaraiHakmilik}" var="i">
                                ${i.hakmilik.idHakmilik}<br>
                            </c:forEach>
                        </display:column>
                        <c:if test="${actionBean.boton ne true}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="remove('${line.permohonanSebelum.idPermohonan}')" onmouseover="this.style.cursor='pointer';">
                                </div>
                            </display:column>
                        </c:if>
                    </display:table>
                </div>
            </fieldset>
        </c:if>
    </div>
</s:form>