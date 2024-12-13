<%--
    Document   : maklumat_pampasan
    Created on : Sep 18, 2010, 7:26:12 PM
    Author     : Rajesh
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    function refreshPagePenerimaanBorang(){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

    function validationTanah() {
        var tanahItem = $("#tanahItem").val();
        var tanahAmaun = $("#tanahAmaun").val();

        if(tanahItem == ""){
            alert('Sila pilih " Item : " terlebih dahulu.');
            $("#tanahItem").focus();
            return false;
        }

        if(tanahAmaun == ""){
            alert('Sila pilih " RM : " terlebih dahulu.');
            $("#tanahAmaun").focus();
            return false;
        }
        return true;
    }

    function validationBngn() {
        <%--alert("bangunan");--%>
        var bngnItem = $("#bngnItem").val();
        var bngnAmaun = $("#bngnAmaun").val();

        if(bngnItem == ""){
            alert('Sila pilih " Item : " terlebih dahulu.');
            $("#bngnItem").focus();
            return false;
        }

        if(bngnAmaun == ""){
            alert('Sila pilih " RM : " terlebih dahulu.');
            $("#bngnAmaun").focus();
            return false;
        }
        return true;
    }

    function validationLain() {
        var lainItem = $("#lainItem").val();
        var lainAmaun = $("#lainAmaun").val();

        if(lainItem == ""){
            alert('Sila pilih " Item : " terlebih dahulu.');
            $("#lainItem").focus();
            return false;
        }

        if(lainAmaun == ""){
            alert('Sila pilih " RM : " terlebih dahulu.');
            $("#lainAmaun").focus();
            return false;
        }
        return true;
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
    function removeNilai(idPenilaian)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/maklumatPampasan?deleteNilai&idPenilaian='+idPenilaian;
            $.get(url,
            function(data){
                $('#page_div').html(data);
    <%--self.opener.refreshPageTanahRizab();--%>
                },'html');
            }
        }
</script>
<s:form beanclass="etanah.view.stripes.pengambilan.MaklumatPampasanActionBean">
    <div  id="hakmilik_details">
        <s:messages/>
        <s:errors/>

        <fieldset class="aras1"><br/>
            <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/maklumatPampasan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.MaklumatPampasanActionBean"
                                event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                            <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
                    </display:column>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                </display:table>
            </div>
        </fieldset>
        <br /><br />

        <c:if test="${actionBean.hakmilik ne null}">
            <fieldset class="aras1">
                <legend>Tuan Tanah</legend><br />
                <div align="center">*Nama tuan tanah boleh diklik sekiranya tuan tanah bersetuju.</div>
                <div align="center">
                    Sila masukkan nilaian untuk setiap Tuan Tanah.
                    <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                        <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                        <display:column property="idHakmilik" title="ID Hakmilik" />
                        <display:column property="noLot" title="Nombor Lot/PT" />
                        <display:column title="Daerah" property="daerah.nama" class="daerah" />
                        <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                                <c:forEach items="${actionBean.perbicaraanKehadiranList}" var="senarai">
                                <c:if test="${senarai.bantahElektrik eq '0'}">
                                    <s:link beanclass="etanah.view.stripes.pengambilan.MaklumatPampasanActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="idPihak" value="${senarai.pihak.pihak.idPihak}"/>
                                        <s:param name="idMP" value="${senarai.pihak.idPermohonanPihak}"/>
                                        ${senarai.pihak.pihak.nama}
                                    </s:link>
                                    <br/>
                                    No KP ${senarai.pihak.pihak.noPengenalan}
                                    
                                </c:if>
                                <br/>
                                </c:forEach>
                                
                        </display:column>
                        <display:column title="Bantahan" style="vertical-align:baseline">
                                <c:forEach items="${actionBean.perbicaraanKehadiranList}" var="senarai">
                                <c:if test="${senarai.bantahElektrik eq '1'}">
                                    <s:link beanclass="etanah.view.stripes.pengambilan.MaklumatPampasanActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="idPihak" value="${senarai.pihak.pihak.idPihak}"/>
                                        <s:param name="idMP" value="${senarai.pihak.idPermohonanPihak}"/>
                                        ${senarai.pihak.pihak.nama}
                                    </s:link>
                                    <br/>
                                    No KP ${senarai.pihak.pihak.noPengenalan}

                                </c:if>
                                
                                </c:forEach>
                                <c:if test="${senarai.bantahElektrik eq '0'}">
                                   -
                                </c:if>

                        </display:column>
                        <display:column title="Luas Diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
                    </display:table>
                </div>
                <br /><br />
            </fieldset><br />
        </c:if>
        <%--  <c:if test="${actionBean.permohonanPihak ne null}">--%>
        <c:if test="${showDetails}">
            
            <table >
                <tr>
                    <td width="20%"><label  >Perbicaraan Pengambilan No :</label></td>
                    <td>${actionBean.hakmilikPerbicaraan.idPerbicaraan}<br /></td>
                </tr>
                <tr>
                    <td width="20%"><label  >Tarikh Perbicaraan :</label></td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hakmilikPerbicaraan.tarikhBicara }"/><br /></td>
                </tr>
            </table>
            <br />

            <%-- <table >
                 <tr><td align="right"><font color="#003194" style="Tahoma" size="2px"><b>Tanah :</b></font></td></tr>
                 <tr>
                      <s:hidden name="idPermohonanPihak" value ="${actionBean.idPermohonanPihak}" />
                      <s:hidden name="idHakmilik" value ="${actionBean.idHakmilik}" />
                     <td><label for="tanahItem" >Item :</label></td>
                     <td width="19%"><s:text name="tanahItem" size="20" id="tanahItem"/><br/></td>
                     <td ><font color="#003194" style="Tahoma" size="2px"><b>RM :</b></font></td>
                     <td width="18%"><s:text name="tanahAmaun" size="20" id="tanahAmaun" onkeyup="validateNumber(this,this.value);"/></td>
                     <td><s:button name="simpanTanah" id="save" value="Simpan" class="btn" onclick="if(validationTanah())doSubmit(this.form, this.name, 'page_div')"/></td>
                 </tr>
             </table>--%>

            <%-- <br/>

      <div align="center">
          <display:table class="tablecloth" name="${actionBean.penilaianTanahList}" cellpadding="0" cellspacing="0" id="tbl1">
              <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
              <display:column property="item" title="Item" />
              <display:column property="amaun" title="Amaun (RM)" />
              <display:column title="Kemaskini">
                  <div align="center">
                      <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                           id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup();"/>
                  </div>
              </display:column>
          </display:table>
      </div>
            --%>
            <br/><br/>

            <table >
                <tr><td align="right"><font color="#003194" style="Tahoma" size="2px"><b>Nilaian Bangunan :</b></font></td></tr>
                <tr>
                    <s:hidden name="idPermohonanPihak" value ="${actionBean.idPermohonanPihak}" />
                    <s:hidden name="idHakmilik" value ="${actionBean.idHakmilik}" />
                    <td><label for="bngnItem" >Item :</label></td>
                    <td width="19%"><s:text name="bngnItem" size="20" id="bngnItem"/><br/></td>
                    <td ><font color="#003194" style="Tahoma" size="2px"><b>RM :</b></font></td>
                    <td width="18%"><s:text name="bngnAmaun" size="20" id="bngnAmaun" onkeyup="validateNumber(this,this.value);"/></td>
                    <td><s:button name="simpanBngn" id="save" value="Simpan" class="btn" onclick="if(validationBngn())doSubmit(this.form, this.name, 'page_div')"/></td>

                </tr>
            </table>

            <br/><br/>

            <div align="center">
                <display:table class="tablecloth" name="${actionBean.penilaianBngnList}" cellpadding="0" cellspacing="0" id="tbl1">
                    <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                    <display:column property="item" title="Item" />
                    <display:column property="amaun" title="Amaun (RM)" />
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeNilai('${tbl1.idPenilaian}');" />
                        </div>
                    </display:column>
                </display:table>
            </div>
            <br /><br/>

            <table >
                <tr><td align="right"><font color="#003194" style="Tahoma" size="2px"><b>Nilaian Lain-Lain :</b></font></td></tr>
                <tr>
                    <s:hidden name="idPermohonanPihak" value ="${actionBean.idPermohonanPihak}" />
                    <s:hidden name="idHakmilik" value ="${actionBean.idHakmilik}" />
                    <td><label for="lainItem">Item :</label></td>
                    <td width="19%"><s:text name="lainItem" size="20" id="lainItem"/><br/></td>
                    <td ><font color="#003194" style="Tahoma" size="2px"><b>RM :</b></font></td>
                    <td width="18%"><s:text name="lainAmaun" size="20" id="lainAmaun" onkeyup="validateNumber(this,this.value);"/></td>
                    <td><s:button name="simpanLain" id="save" value="Simpan" class="btn" onclick="if(validationLain())doSubmit(this.form, this.name, 'page_div')"/></td>
                </tr>
            </table>

            <br/><br/>

            <div align="center">
                <display:table class="tablecloth" name="${actionBean.penilaianLainList}" cellpadding="0" cellspacing="0" id="tbl1">
                    <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                    <display:column property="item" title="Item" />
                    <display:column property="amaun" title="Amaun (RM)" />
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeNilai('${tbl1.idPenilaian}');" />
                        </div>
                    </display:column>
                </display:table>
            </div>

            <br/><br/>

            <div align="center">
                <display:table class="tablecloth" name="${actionBean.permohonanPihak}" cellpadding="0" cellspacing="0" id="tbl1">
                    <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                    <display:column title="Tuan Tanah" >${tbl1.pihak.nama}</display:column>
                    <display:column  title="Item Nilaian (RM)" >
                        <c:set value="${tbl1.syerPembilang}" var="a"/>
                        <c:set value="${tbl1.syerPenyebut}" var="b"/>
                        <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                        <%--<c:set value="${actionBean.hakmilikPerbicaraan.keputusanNilai}" var="d"/>--%>
                        <%-- <c:set value="${d/c}" var="e"/>--%>
                        <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                        <c:set value="${d*(a/b)}" var="e"/>
                        <b>Tanah : RM <fmt:formatNumber pattern="#,##0.00" value="${e}"/></b><br/>
                        <b>Bangunan : RM ${actionBean.itemNilaianBngn}</b><br/>
                        <b>Lain - lain : RM ${actionBean.itemNilaianLain}</b>
                    </display:column>
                    <display:column  title="Jumlah Keseluruhan (RM)" >
                        <c:set value="${tbl1.syerPembilang}" var="a"/>
                        <c:set value="${tbl1.syerPenyebut}" var="b"/>
                        <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                        <%--<c:set value="${actionBean.hakmilikPerbicaraan.keputusanNilai}" var="d"/>--%>
                        <%-- <c:set value="${d/c}" var="e"/>--%>
                        <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                        <c:set value="${d*(a/b)}" var="e"/>
                        <c:set value="${actionBean.jumlah}" var="h"/>
                        <%--<b>RM ${actionBean.jumlah}</b>--%>
                        <b>RM <fmt:formatNumber pattern="#,##0.00" value="${e+h}"/></b>
                    </display:column>
                </display:table>
                <c:if test="${actionBean.permohonanPihak ne null && actionBean.kodKedesakan eq 'DE'}">
                    <table>
                        <%--   <tr>
                               <td colspan="6">Tempoh dari Borang H :</td>
                           </tr>
                           <tr>
                               <td>Dari : </td>
                               <td>
                                   <s:text id="BorangHDari" name="BorangHDari"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhTerimaH}"/></s:text>
                               </td>
                               <td>Terkini : </td>
                               <td>
                                   <s:text id="BorangHKini" name="BorangHKini"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.calendar}"/></s:text>
                               </td>
                               <td>Jurang Tempoh : </td>
                               <td>
                                   <s:text id="tempohH" name="tempohH" value="${actionBean.tempohH}" />
                               </td>
                           </tr>--%>
                        <tr>
                            <c:if test="${actionBean.kodKedesakan eq 'DE'}">


                                <td colspan="6">Tempoh dari Borang I :</td>
                            </tr>
                            <tr>
                                <td>Dari : </td>
                                <td>
                                    <s:text id="BorangIDari" name="BorangIDari"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhTerimaI}"/></s:text>
                                </td>
                                <td>Terkini : </td>
                                <td><s:text id="BorangIKini" name="BorangIKini"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.calendar}"/></s:text></td>
                                <td>Jurang Tempoh : </td>
                                <td><s:text id="tempohI" name="tempohI" value="${actionBean.tempohI}"/></td>
                            </tr>
                            <tr>
                                <td>Faedah 8% per anum :RM </td>
                                <td>
                                    <c:set value="${actionBean.tempohIyear}" var="y"/>
                                    <c:set value="${actionBean.tempohImonth}" var="m"/>
                                    <c:set value="${(e+h)*y*0.08}" var="i"/>
                                    <c:set value="${(e+h)*m/12*0.08}" var="j"/>
                                    <c:set value="${i+j}" var="l"/>
                                    <s:text id="faedah" name="faedah" value="${l}"/> 
                                    <%--    <s:text id="faedah" name="faedah" value="${actionBean.faedahH}"/> --%>
                                </td>
                            </tr>
                            <tr>
                                <td>Jumlah :RM </td>
                                <td><s:text id="jumlahs" name="jumlahs" value="${l+e+h}"/></td>

                            </tr>
                            <br/>

                        </c:if>
                    </table>
                </c:if>
            </div>
            <%--</c:if>--%>
        </c:if>

    </div>
</s:form>