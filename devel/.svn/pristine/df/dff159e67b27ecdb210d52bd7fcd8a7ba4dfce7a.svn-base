<%-- 
    Document   : lelong_batal
    Created on : Sep 6, 2010, 12:51:41 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    
    function remove(val){
        if(confirm("Adakah Anda Pasti?")){
            var url = '${pageContext.request.contextPath}/lelong/keputusan_tangguh_batal?delete&idMohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html'); 
        }
    }


</script>

<s:form beanclass="etanah.view.stripes.lelong.KeputusanTangguhBatalActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle displaytag">
        <c:if test="${actionBean.boton ne true}">
            <fieldset class="aras1">
                <legend>
                    Senarai Urusan Terlibat
                </legend>

                <div class="subtitle" align="center">
                    <display:table class="tablecloth" name="${actionBean.listHP2}" pagesize="10" requestURI="/lelong/keputusan_tangguh_batal" id="line">
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

                <c:if test="${fn:length(actionBean.listHP2) > 0}"><%--
                    <table style="margin-left: auto; margin-right: auto;">
            <tr>
                <td>&nbsp;</td>--%>
                    <%--<td>--%>

                    <p class=instr align="center">
                        *<em>Petunjuk : Sila Pilih Satu Sahaja</em>
                    </p><br>
                    <div align="center">
                        <s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </div>
                </c:if>

                <%--      </td>
                  </tr>
              </table>--%>
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