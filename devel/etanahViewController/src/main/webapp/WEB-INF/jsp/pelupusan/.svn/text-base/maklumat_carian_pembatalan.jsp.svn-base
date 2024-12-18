<%-- 
    Document   : maklumat_rayuan_mlk
    Created on : Jun 15, 2011, 12:50:07 PM
    Author     : Akmal
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function cariId(idPermohonanSebelum)
    {
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_rayuan?searchId&idPermohonanSebelum'+idPermohonanSebelum;
        $.get(url,
        function(data)
        {
            $('#page_div').html(data);
            self.refreshCari();
        },'html');
    
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatCarianPembatalanActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${!status}"> 
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Cari Permohonan Dahulu (0401DIS2010150008) </legend>
                
                
                    <div align="center">
                
                    ID Permohonan :<s:text name="idPermohonanSebelum" size="40"/> 
                    <s:button name="searchId" value="Cari" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
         
                </div>
                
            </fieldset>
        </div>
    </c:if>
    <c:if test="${status}">
        <div class="subtitle">
            <fieldset class="aras1"> 
                <legend>Maklumat Permohonan Terdahulu</legend>
                <s:hidden name="idPermohonanSebelum"/>

                <p>
                    <label>Id Permohonan :</label>${actionBean.permohonanSebelum.idPermohonan}
                </p>
                <p>
                    <label>Urusan :</label>${actionBean.permohonanSebelum.kodUrusan.nama} 
                </p>
                <p>
                    <label>Tujuan Permohonan :</label>${actionBean.permohonanSebelum.sebab}
                </p>
                <p>               
                    <label>Tarikh Permohonan :</label> 
                    <s:format value="${actionBean.permohonanSebelum.infoAudit.tarikhMasuk}" formatPattern="dd/MM/yyyy"/>
                </p>
                <br/><br/>
            </fieldset>
                <fieldset class="aras1">
                    <legend>Maklumat Tanah</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_rayuan" id="line">
                            <display:column title="Nama Pemilik" style="vertical-align:baseline">${actionBean.pemohon.pihak.nama}</display:column>
                            <display:column title="Jenis Tanah" style="vertical-align:baseline">${line.kategoriTanahBaru.nama}</display:column>                      
                            <display:column title="Nombor Lot/PT" style="vertical-align:baseline">${line.noLot}</display:column>
                            <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                            <display:column title="Daerah" class="daerah" style="vertical-align:baseline">${line.bandarPekanMukimBaru.daerah.nama}</display:column>
                            <display:column title="Bandar/Pekan/Mukim" style="vertical-align:baseline">${line.bandarPekanMukimBaru.nama}</display:column>
                        </display:table>


                    </div>   
                </fieldset>
                <fieldset class="aras1">
                    <legend>Maklumat Pemohon</legend>
                    <s:hidden name="idPermohonan" id="idPermohonan"/>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.pemohon}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_rayuan">
                            <display:column property="pihak.nama" title="Nama"/>
                            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                            <display:column title="Alamat" >${line.pihak.suratAlamat1}
                                <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                                ${line.pihak.suratAlamat2}
                                <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                                ${line.pihak.suratAlamat3}
                                <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                                ${line.pihak.suratAlamat4}</display:column>
                            <display:column property="pihak.suratPoskod" title="Poskod" />
                            <display:column property="pihak.suratNegeri.nama" title="Negeri" />
                        </display:table>
                    </div>
                </fieldset>

                <p>
                    <label>&nbsp;</label>
                    <c:if test="${simpan}">
                        <s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
                    </c:if>
                    <c:if test="${actionBean.status eq false}">
                    <s:button name="refreshCari" value="Cari Semula" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
                    </c:if>
                </p>
            
      </div>
</c:if>
</s:form>
