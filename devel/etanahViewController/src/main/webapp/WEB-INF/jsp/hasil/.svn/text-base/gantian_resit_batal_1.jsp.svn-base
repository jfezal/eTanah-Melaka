<%-- 
    Document   : gantian_resit_batal_1
    Created on : Nov 30, 2009, 10:04:52 AM
    Author     : nurfaizati
--%>

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


<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean" id ="gantian_resit_batal_1">
                


        <table width="100%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Kemasukan Semula Resit</font>
            </div>
        </td>
    </tr>
</table>
           <fieldset class="aras1">
            <legend>
                Carian 
            </legend>
              
                <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font>Sila Masukan Salah Satu Maklumat Berikut
            </div>&nbsp;


              <div class="instr" align="center">
                        <s:errors/>
                    </div>

             <%--        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                 <p>
                     <label for="noAkaun">No Akaun :</label>
                     <s:text name="akaunKredit.noAkaun" size="30"/>
                 </p>
                     </c:if>--%>
       

            <p>
                <label for="No Resit">Nombor Resit :</label>
                <s:text name="dokumenKewangan.idDokumenKewangan" size="30"/>
            </p>


            <p>
                <label>ID Hakmilik :</label>
                <s:text name="hakmilik.idHakmilik" id="hakmilik" size="30"/>
            </p>

               <p>
                <label for="No Resit">ID Permohonan :</label>
                <s:text name="permohonan.idPermohonan"  size="30"/>
            </p>

            <table border="0" width="100%">
                <tr>
                    <td align="right">
                        <s:submit   name="search1" value="Cari" class="btn"/>
                        <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('gantian_resit_batal_1');"/>
                    </td> </tr>
            </table>

        </fieldset>

        <c:if test="${actionBean.flag1}">
            <br>

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Akaun
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.transList}" pagesize="4" cellpadding="0" cellspacing="0" id="line" requestURI="/hasil/kutipan_hasil">

                            <display:column title="Pilih" sortable="true">

                                <%--<div align="center"><s:radio name="idHakmilik" value="${line.akaunKredit.noAkaun}" /></div></display:column>--%>

                            <%--<div align="center"><s:radio name="idHakmilik" value="${line.akaunDebit.noAkaun}" /></div></display:column>--%>
<%--<div align="center"><s:radio name="idHakmilik" value="${line.akaunDebit.hakmilik.idHakmilik}" /></div></display:column>--%>
                          <div align="center"><s:radio name="idPermohonan" value="${line.permohonan.idPermohonan}" /></div></display:column>
                         

                            <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</display:column>
                            <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit" />
                             <%--<c:if test="${line.permohonan.idPermohonan ne null}">--%>
                            <display:column title="ID Permohonan">${line.permohonan.idPermohonan}</display:column>
                        <%--</c:if>--%>
                          <%--  <c:if test="${line.akaunKredit.hakmilik.idHakmilik ne null}">
                                <display:column title="No Hakmilik">${line.akaunKredit.hakmilik.idHakmilik}</display:column>
                            </c:if>
                            <c:if test="${line.akaunDebit.hakmilik.idHakmilik ne null}">
                                <display:column title="No Hakmilik">${line.akaunDebit.hakmilik.idHakmilik}</display:column>
                            </c:if>--%>
                            <display:column  title="Sebab Batal" >
                                ${line.dokumenKewangan.kodBatal.nama} : ${line.dokumenKewangan.catatan}</display:column>
                         
                            <display:column property="dokumenKewangan.status.nama" title="Status"  />
                           
                            <display:column property="dokumenKewangan.tarikhBatal" title="Tarikh Batal"  format="{0,date,dd/MM/yyyy}"  />

                        </display:table>
                    </div>
                </fieldset>


                <table border="0" bgcolor="green" width="100%">
                    <tr>
                        <td align="right">
                            <s:submit name="selectList" value="Terus" class="btn" disabled="${actionBean.btn}"/>
                        </td>
                    </tr>
                </table>

            </div>

        </c:if>
    </s:form>
</div>



