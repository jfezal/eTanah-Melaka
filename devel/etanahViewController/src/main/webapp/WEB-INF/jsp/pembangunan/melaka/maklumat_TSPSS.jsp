<%-- 
    Document   : maklumat_TSPSS
    Created on : Aug 18, 2011, 10:53:45 AM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function searchKodSyaratNyata(index){
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSyaratNyata2&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function searchKodSekatan(index){        
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSekatan2&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1100px,height=700px,scrollbars=yes");
    }
</script>


<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatTSPSSActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Untuk Mengubah Syarat-Syarat Nyata Atau Sekatan Kepentingan
            </legend>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="15">
                    <tr>
                        <td>
                            <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                                           requestURI="/pembangunan/melaka/maklumat_TSPSS" id="line">                            
                            <display:column title="No. Plot" style="vertical-align:top">${line.noPlot}</display:column>
                            <display:column title="Jenis Kategori" style="vertical-align:top">${line.kategoriTanah.nama}</display:column>
                            <c:if test="${edit}">
                                <display:column title="Syarat Nyata" style="vertical-align:baseline">                                                                        
                                    <s:textarea name="kodSyaratNyataBaru" id="syaratNyata${line_rowNum-1}" value="${line.kodSyaratNyata.kod} - ${line.kodSyaratNyata.syarat}" readonly="readonly" rows="8" cols="50" class="textBesa" />
                                    <s:hidden name="kodSyaratNyata${line_rowNum-1}" id="kod${line_rowNum-1}" value="${line.kodSyaratNyata.kod}"/>
                                    <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSyaratNyata(${line_rowNum-1})" />
                                </display:column>
                                <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">                                    
                                    <s:textarea name="kodSekatanKepentinganBaru" id="sekatan${line_rowNum-1}" value="${line.kodSekatanKepentingan.kod} - ${line.kodSekatanKepentingan.sekatan}" readonly="readonly" rows="8" cols="50" class="textBesa"/>
                                    <s:hidden name="kodSekatan${line_rowNum-1}"  id="kodSktn${line_rowNum-1}"  value="${line.kodSekatanKepentingan.kod}"/>
                                    <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSekatan(${line_rowNum-1})" />
                                </display:column>
                                <display:column title="Batal Ungkapan 'Padi', 'Getah', 'Kampung', dsb" style="vertical-align:baseline">
                                    <s:textarea name="senaraiPlotPelan[${line_rowNum-1}].ungkapanBatal" cols="20" rows="8"/>
                                </display:column>
                            </c:if>
                            <c:if test="${!edit}">
                                <display:column title="Syarat Nyata" style="vertical-align:baseline">${line.kodSyaratNyata.kod} - ${line.kodSyaratNyata.syarat}</display:column>
                                <display:column title="Sekatan Kepentingan" style="vertical-align:baseline">${line.kodSekatanKepentingan.kod} - ${line.kodSekatanKepentingan.sekatan}</display:column>
                                <display:column title="Batal Ungkapan 'Padi', 'Getah', 'Kampung', dsb" style="vertical-align:baseline">${line.ungkapanBatal}</display:column>
                            </c:if>
                            </display:table>
                        </td>
                    </tr>
                </table>
                <c:if test="${edit}">
                <p>                    
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
            </div>
        </fieldset>
    </div>
</s:form>