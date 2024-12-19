<%-- 
    Document   : carian_permohonan_lalu_lalang
    Created on : Dec 13, 2013, 10:18:10 AM
    Author     : afham
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        // carian hakmilik start
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}),dialogInit('Carian Hakmilik'),$('#catatanKegunaan').hide();
        //carian hakmilik end
        
        /*
         * CUSTOM BY URUSAN   
         *  
         */
        var len = $(".daerah").length;
       
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/pembangunan/maklumat_hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.CarianPermohonanHaklalulalangActionBean">
    <s:messages/>
    <s:errors/>

    <fieldset class="aras1">
        <c:if test="${!status}">

            <legend>Carian Id Permohonan </legend>

            <br/>
            <br/>
            <label>ID Permohonan :</label>
            <s:text name="idPermohonanSebelum" id="idPermohonanSebelum" onkeyup="this.value = this.value.toUpperCase();"/><br/><br/>
            <center><s:button name="cariPermohonan" id="cari" value="Cari" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/> </center>
        </fieldset>
    </c:if>
    <c:if test="${status}">

        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <s:hidden name="idPermohonanSebelum" id="idPermohonanSebelum"/>
            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.permohonanSebelum}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/carian_permohonan_lalulalang">

                    <display:column title="ID Permohonan">${actionBean.permohonanSebelum.idPermohonan}</display:column>
                    <display:column title="Urusan">${actionBean.permohonanSebelum.kodUrusan.nama}</display:column>


                </display:table>
            </div>
        </fieldset>
        <fieldset class="aras1">
            <legend>Perihal Mengenai Tanah Pemilik</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listHakmilikPermohonan}"
                               id="line" pagesize="10" style="width:95%"
                               requestURI="${pageContext.request.contextPath}/pelupusan/carian_permohonan_lalulalang">                    
                    <c:set var="row_num" value="${row_num+1}"/>
                    <c:set var="bcolor" value=""/>
                    <c:set var="title" value="ID Permohonan"/>
                    <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                        ${row_num}
                    </display:column>
                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                        <display:column property="hakmilik.idHakmilik" style="text-decoration:underline" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    </c:if>
                    <c:if test="${line.hakmilik.idHakmilik eq null}">
                        <display:column title="ID Hakmilik">                                        
                            <center><b>-</b></center>  
                        </display:column>        
                    </c:if>
                    <display:column title="No Lot/PT">
                        <c:if test="${line.hakmilik.idHakmilik ne null}">
                            ${line.hakmilik.lot.nama} ${line.hakmilik.noLot} 
                        </c:if>
                        <c:if test="${line.hakmilik.idHakmilik eq null and line.noLot ne null}">
                            ${line.lot.nama} ${line.noLot} 
                        </c:if>
                        <c:if test="${line.hakmilik.idHakmilik eq null and line.noLot eq null}">
                            <center><b>-</b></center>
                        </c:if>
                    </display:column>
                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    </c:if>
                    <c:if test="${line.hakmilik.idHakmilik eq null}">
                        <display:column title="Daerah">                        
                            ${line.bandarPekanMukimBaru.daerah.nama}                    
                        </display:column>        
                    </c:if>

                    <display:column title="Bandar/Pekan/Mukim">  
                        <c:if test="${line.hakmilik.idHakmilik ne null}">
                            ${line.hakmilik.bandarPekanMukim.nama}  
                        </c:if>
                        <c:if test="${line.hakmilik.idHakmilik eq null}">
                            ${line.bandarPekanMukimBaru.nama}    
                        </c:if>
                    </display:column>      
                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                        <display:column title="Luas Tanah">
                            <center>
                                <s:format value="${line.hakmilik.luas}" formatPattern="#,###,##0.0000"/> ${line.hakmilik.kodUnitLuas.nama}
                            </center>     
                        </display:column>
                    </c:if>            
                </display:table>

            </div>

        </fieldset>
        <fieldset class="aras1">
            <legend>Perihal Mengenai Tanah Lalulalang</legend>

            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.listHakmilikPermohonanLalulalang}"
                               id="line" pagesize="10" style="width:95%"
                               requestURI="${pageContext.request.contextPath}/pelupusan/carian_permohonan_lalulalang">                    
                    <c:set var="row_num" value="${row_num+1}"/>
                    <c:set var="bcolor" value=""/>
                    <c:set var="title" value="ID Permohonan"/>
                    <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                        ${row_num}
                    </display:column>
                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                        <display:column property="hakmilik.idHakmilik" style="text-decoration:underline" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    </c:if>
                    <c:if test="${line.hakmilik.idHakmilik eq null}">
                        <display:column title="ID Hakmilik">                                        
                            <center><b>-</b></center>  
                        </display:column>        
                    </c:if>
                    <display:column title="No Lot/PT">
                        <c:if test="${line.hakmilik.idHakmilik ne null}">
                            ${line.hakmilik.lot.nama} ${line.hakmilik.noLot} 
                        </c:if>
                        <c:if test="${line.hakmilik.idHakmilik eq null and line.noLot ne null}">
                            ${line.lot.nama} ${line.noLot} 
                        </c:if>
                        <c:if test="${line.hakmilik.idHakmilik eq null and line.noLot eq null}">
                            <center><b>-</b></center>
                        </c:if>
                    </display:column>
                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    </c:if>
                    <c:if test="${line.hakmilik.idHakmilik eq null}">
                        <display:column title="Daerah">                        
                            ${line.bandarPekanMukimBaru.daerah.nama}                    
                        </display:column>        
                    </c:if>

                    <display:column title="Bandar/Pekan/Mukim">  
                        <c:if test="${line.hakmilik.idHakmilik ne null}">
                            ${line.hakmilik.bandarPekanMukim.nama}  
                        </c:if>
                        <c:if test="${line.hakmilik.idHakmilik eq null}">
                            ${line.bandarPekanMukimBaru.nama}    
                        </c:if>
                    </display:column>      
                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                        <display:column title="Luas Tanah">
                            <center>
                                <s:format value="${line.hakmilik.luas}" formatPattern="#,###,##0.0000"/> ${line.hakmilik.kodUnitLuas.nama}
                            </center>     
                        </display:column>
                    </c:if>            
                </display:table>
            </div>

        </fieldset>
        <center>
            <c:if test="${!simpan}">
                <s:button name="simpanPermohonanSebelum" id="cari" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button name="cariSemula" id="cari" value="Cari Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/> 
            </c:if>
             </center>
    </c:if>
</s:form>

