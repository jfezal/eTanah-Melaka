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

        $('#displayBox').hide();
        $("#btn").hide();
    });
    function validate(stat,id){
        $("#btn").show();
        var permohonan = ${actionBean.btn};
        if(stat == 'B'){
            alert("Nombor Resit "+id +" telah Batal.");
            $("#btn").attr("disabled", "true");
        }else if(permohonan){
            alert("Tidak dibenarkan membuat pembatalan.");
            $("#btn").attr("disabled", "true");
        }else{
            $("#btn").removeAttr("disabled");
        }
    }
</script>
<div class="subtitle">
    <table width="100%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Pembatalan Resit</font>
                </div>
            </td>
        </tr>
    </table>
    <s:form beanclass="etanah.view.stripes.hasil.UtilitiPembatalanResitActionBean" id="pembatalan_resit_1">
        <s:errors/>
        <s:messages/>
        <s:hidden name= "kodNegeri"/>
        <%--<s:form action="/pembatalan_resit" id="pembatalan_resit_1">--%>
        <fieldset class="aras1">
            <legend>
                Carian Maklumat
            </legend>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font>Sila Masukan Salah Satu Maklumat Berikut
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

        <c:if test="${actionBean.flag}">
            <br>

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Akaun
                    </legend>
                    <div class="content" align="center">
                        <%--<display:table name="${actionBean.list}" id="line" class="tablecloth">--%>
                        <display:table class="tablecloth" name="${actionBean.dokKewList}" pagesize="4" cellpadding="0" cellspacing="0" id="line" requestURI="/hasil/pembatalan_resit">

                            <display:column title="Pilih" sortable="true">
                                <div align="center"><s:radio name="idDokumenKewangan" value="${line.idDokumenKewangan}" onclick="validate('${line.status.kod}','${line.idDokumenKewangan}','${line.akaun.hakmilik.idHakmilik}')"/></div></display:column>
                            <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</div></display:column>

                            <c:if test="${line.noRujukanManual ne null}">
                                <display:column property="noRujukanManual" title="Nombor Resit Kew. 38" />
                            </c:if>
                            <c:if test="${line.noRujukanManual eq null}">
                                <display:column property="idDokumenKewangan" title="Nombor Resit" />
                            </c:if>                        

                            <c:if test="${actionBean.kodNegeri eq 'melaka' and line.akaun.permohonan.idPermohonan eq null}">
                                <display:column property="akaun.noAkaun" title="No Akaun" />
                            </c:if>

                            <c:if test="${line.akaun.hakmilik.idHakmilik ne null}">
                                <display:column title="ID Hakmilik">${line.akaun.hakmilik.idHakmilik}</display:column>
                            </c:if>
                            <c:if test="${line.akaun.permohonan.idPermohonan ne null}">
                                <display:column property="akaun.permohonan.idPermohonan" title="ID Permohonan/Perserahan"  />
                            </c:if>
                            <display:column property="infoAudit.tarikhMasuk" title="Tarikh Transaksi" format="{0,date,dd/MM/yyyy,hh:mm aa}" style="text-align:right"/>
                            <display:column property="status.nama" title="Status"  />


                        </display:table>

                        <table border="0" bgcolor="green" width="100%">
                            <tr>
                                <td align="right">

                                    <s:submit name="selectList" value="Terus" class="btn" id="btn" disabled="${actionBean.btn}"/>

                                </td></tr> </table>

                    </div>
                </fieldset>
            </div>

        </div>
    </c:if>
</s:form>








