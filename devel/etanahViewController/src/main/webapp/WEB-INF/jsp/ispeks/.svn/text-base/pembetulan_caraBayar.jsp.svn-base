<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {


    });


</script>
<div class="subtitle">
    <table width="100%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Pembetulan Cara Bayar Dari Debit Kad Ke Kredit Kad ATAU Dari Kredit Kad Ke Debit Kad</font>
                </div>
            </td>
        </tr>
    </table>
    <s:form beanclass="etanah.view.stripes.ispeks.UtilitiPembetulanCaraBayarActionBean" id="pembatalan_resit_1">
        <s:errors/>
        <s:messages/>
        <s:hidden name= "kodNegeri"/>
        <fieldset class="aras1">
            <legend>
                Carian Maklumat
            </legend>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font>Sila Masukan No Resit
            </div>&nbsp;

            <div class="instr" align="center">                
            </div>

            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <p>
                    <label for="No Resit">No Resit :</label>
                    <s:text name="idDokumenKewangan" size="30"/>
                </p>
            </c:if>


            <table border="0" width="100%">
                <tr>
                    <td align="right">
                        <s:submit   name="search" value="Cari" class="btn"/>
                        <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('pembatalan_resit_1');"/>
                    </td>
                </tr>
            </table>

        </fieldset>
            <br>

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Akaun
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listDK}" pagesize="4" cellpadding="0" cellspacing="0" id="line" requestURI="/utiliti/ispeks/pembetulanCaraBayar">

                            <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</div></display:column>

                            <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit" />  

                            <c:if test="${line.caraBayaran.kodCaraBayaran.kod ne 'DK' && line.caraBayaran.kodCaraBayaran.kod ne 'KK'}">
                                <display:column property="caraBayaran.kodCaraBayaran.nama" title="Cara Bayaran" />  
                            </c:if>

                            <c:if test="${line.caraBayaran.kodCaraBayaran.kod eq 'DK' || line.caraBayaran.kodCaraBayaran.kod eq 'KK'}">
                                    <display:column title="Cara Bayaran" >
                                        <s:select class="CaraByr" name="caraBayaran.kodCaraBayaran.kod" value="${line.caraBayaran.kodCaraBayaran.kod}">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:option value="DK">Debit Kad</s:option>
                                            <s:option value="KK">Kredit Kad</s:option>
                                        </s:select>
                                    </display:column>
                            </c:if>


                            <c:if test="${actionBean.kodNegeri eq 'melaka' and line.dokumenKewangan.akaun.permohonan.idPermohonan eq null}">
                                <display:column property="dokumenKewangan.akaun.noAkaun" title="No Akaun" />
                            </c:if>

                            <c:if test="${line.dokumenKewangan.akaun.hakmilik.idHakmilik ne null}">
                                <display:column title="ID Hakmilik">${line.dokumenKewangan.akaun.hakmilik.idHakmilik}</display:column>
                            </c:if>
                            <c:if test="${line.dokumenKewangan.akaun.permohonan.idPermohonan ne null}">
                                <display:column property="dokumenKewanganakaun.permohonan.idPermohonan" title="ID Permohonan/Perserahan"  />
                            </c:if>
                            <display:column property="infoAudit.tarikhMasuk" title="Tarikh Transaksi" format="{0,date,dd/MM/yyyy,hh:mm aa}" style="text-align:right"/>
                            <display:column property="dokumenKewangan.status.nama" title="Status"  />


                        </display:table>
                        <c:if test="${line.caraBayaran.kodCaraBayaran.kod eq 'DK' || line.caraBayaran.kodCaraBayaran.kod eq 'KK'}">
                            <table border="0" bgcolor="green" width="100%">
                                <tr>
                                    <td align="right">

                                        <s:submit name="kemaskiniCaraByr" value="Simpan" class="btn"/>

                                    </td>
                                </tr> 
                            </table>
                        </c:if>


                    </div>
                </fieldset>
            </div>

        </div>
</s:form>








