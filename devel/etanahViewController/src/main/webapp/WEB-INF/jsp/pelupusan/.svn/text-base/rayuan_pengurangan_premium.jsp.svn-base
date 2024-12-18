<%-- 
    Document   : rayuan_pengurangan_premium
    Created on : Sep 3, 2010, 3:45:50 PM
    Author     : sitifariza.hanim
    modified   : Srinivas
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    $(document).ready( function(){
         if($('#ulasan').val() == 'N'){
            $('#nominal').show() ;
            $('#bukanNominal').hide() ;
        }
        else {
            $('#nominal').hide() ;
            $('#bukanNominal').show() ;
            
        }
        if( ${actionBean.permohonan.catatan eq 'PR'}){
         $('#tempohBulanDiv').hide() ;
         if(${actionBean.permohonan.tempohBulan ne ''}){
             document.getElementById('tempohBulan').value = '';
         }
         document.getElementById('tempohBulanDiv').style.visibility = "hidden";
       }else{
           $('#tempohBulanDiv').show() ;
           document.getElementById('tempohBulanDiv').style.visibility = "visible";
       }

    });
    
    function forNominal(){
//        alert($('#ulasan').val());
        if($('#ulasan').val() == 'N'){
              $('#nominal').show() ;
            $('#bukanNominal').hide() ;
            $('#nilai').val(0) ;
        }
        else {
             $('#nominal').hide() ;
            $('#bukanNominal').show() ;
        }
    }
    function forDipohon(catanan){
       if(catanan == 'LB' || catanan == 'PL'){
              $('#tempohBulanDiv').show() ;
              document.getElementById('tempohBulanDiv').style.visibility = "visible";

        }
        else {
             $('#tempohBulanDiv').hide() ;
             document.getElementById('tempohBulanDiv').style.visibility = "hidden";
       }
    }
    jQuery.fn.ForceNumberOnly = function() {
                return this.each(function()     {
                    $(this).keydown(function(e)         {
                        var key = e.charCode || e.keyCode || 0; // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
                       return (
                        key == 8 ||
                            key == 9 ||
                            key == 46 ||
                            (key >= 37 && key <= 40) ||
                            (key >= 48 && key <= 57) ||
                            (key >= 96 && key <= 105));
                    });
                });
            };
            jQuery('.numbersOnly').ForceNumberOnly();
</script>
<s:form  beanclass="etanah.view.stripes.pelupusan.PermohonanRAYTActionBean">
<s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Rayuan Pengurangan Premium</legend>
            <c:if test="${edit}">
                <table align="center">
                    <tr>
                            <td><label>Rayuan untuk :</label>
                               <s:select name="permohonan.catatan" id="catatan" onchange="forDipohon(this.value);">
                                    <s:option value="PR" selected="selected">Pengurangan Premium</s:option>
                                    <s:option value="LB">Lanjutan Tempoh Bayaran</s:option>
                                    <s:option value="PL">Pengurangan dan Lanjut Tempoh Bayaran</s:option>
                                </s:select>
                            </td>
                     </tr>
                     <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'RYKN'}">
                         <tr>
                            <td><label>Bayaran Asal Yang Perlu Dijelaskan :</label>
                               RM <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/>&nbsp;</td>
                        </tr>
                     </c:if>                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">
                        <tr>
                            <td><label>Bayaran Asal Yang Perlu Dijelaskan :</label>
                               RM <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKosSemasa.amaunTuntutan}"/>&nbsp;</td>
                        </tr>
                      <tr>
                        <td><label>Bayaran Yang Telah Dijelaskan :</label>
                           RM <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKosSemasa.amaunSebenar}"/>&nbsp;</td>
                    </tr>  
                    <tr>
                        <td><label>Bayaran Baki Yang Belum Dijelaskan :</label>
                           RM <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKosSemasa.amaunTuntutan - actionBean.permohonanTuntutanKosSemasa.amaunSebenar}"/>&nbsp;</td>
                    </tr>
                    </c:if>
                    <tr>
                        <%-- <td>
                             <label>Bayaran/Diskaun Yang Ingin Dirayukan(RM) :</label>
                            <s:select name="permohonan.noMahkamah" id="ulasan" onchange="forNominal();">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="N">Nominal</s:option>
                            <s:option value="0.25">25%</s:option>
                            <s:option value="0.5">50%</s:option>
                            </s:select>
                        </td> --%>
                        <td>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'RYKN'}">
                                <label>Bayaran/Diskaun Yang Ingin Dirayukan(RM) :</label>
                                <s:select name="permohonan.noMahkamah" id="ulasan" onchange="forNominal();">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="N">Nominal</s:option>
                                <s:option value="0.25">25%</s:option>
                                <s:option value="0.5">50%</s:option>
                                </s:select>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">
                                <c:if test="${actionBean.editPTD}">
                                    <label>Bayaran/Diskaun Yang Ingin Dirayukan(RM) :</label>
                                    <s:select name="permohonan.noMahkamah" id="ulasan" onchange="forNominal();">
                                    <s:option value="N">Nominal</s:option>
                                   <%-- <s:option value="0.25">25%</s:option>
                                    <s:option value="0.5">50%</s:option> --%>
                                    </s:select>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="bukanNominal"> 
                                <label>Bayaran Yang Ingin Dirayukan(RM) :</label>
                                <s:format value="${actionBean.permohonan.nilaiDipohon}" formatPattern="#,##0.00"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="nominal">
                                    <label>Bayaran Yang Ingin Dirayukan(RM) :</label>
                                    <s:text name="permohonan.nilaiDipohon" id="nilai"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                                   <div id="tempohBulanDiv" style="visibility: hidden;">
                                    <label>Tempoh dipohon :</label>
                                    
                                    <s:text name="permohonan.tempohBulan" id="tempohBulan" maxlength="2" class="numbersOnly"  /> Bulan
                                    </div>

                        </td>
                    </tr>
                    <tr>
                        <td>
                           <label>Butir-butir Rayuan</label>                        
                          <s:textarea name="permohonan.sebab" cols="95" rows="10" />
                          </td>
                    </tr>
            </table>
            
                <p>
                    <label>&nbsp;</label>

                    <s:button name="simpan3" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                </p>
                </c:if>
                  <c:if test="${!edit}">
                         <p>
                             <label>Rayuan untuk :</label>
                            <c:if test="${actionBean.permohonan.catatan eq 'PR'}">
                             Pengurangan Premium
                            </c:if>
                             <c:if test="${actionBean.permohonan.catatan eq 'LB'}">
                             Lanjutan Tempoh Bayaran
                            </c:if>
                             <c:if test="${actionBean.permohonan.catatan eq 'PL'}">
                             Pengurangan dan Lanjut Tempoh Bayaran
                            </c:if>

                         </p>
                      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">
                          
                          <p>
                            <label>Bayaran Asal Yang Perlu Dijelaskan :</label>
                               RM <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKosSemasa.amaunTuntutan}"/>&nbsp;
                               
                          </p>
                          <p>
                            <label>Bayaran Yang Telah Dijelaskan :</label>
                               RM <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKosSemasa.amaunSebenar}"/>&nbsp;
                          </p>
                          <p>
                            <label>Bayaran Baki Yang Belum Dijelaskan :</label>
                               RM <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKosSemasa.amaunTuntutan - actionBean.permohonanTuntutanKosSemasa.amaunSebenar}"/>&nbsp;
                          </p>
                      </c:if>
                      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'RYKN'}">
                          <p>
                            <label>Bayaran Asal Yang Perlu Dijelaskan :</label>
                            RM <s:format formatPattern="###,###,###.00" value="${actionBean.permohonanTuntutanKos.amaunTuntutan}"/> &nbsp;
                          </p>
                      </c:if>
            
            <p>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'RYKN'}">
                    <label>Bayaran Yang Ingin Dirayukan(RM) :</label>
                    ${actionBean.permohonan.nilaiDipohon}
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">
                    <c:if test="${actionBean.editPTD}">
                        <label>Bayaran Yang Ingin Dirayukan(RM) :</label>
                        ${actionBean.permohonan.nilaiDipohon}
                    </c:if>
                </c:if>
            </p>
            <p>
                <c:if test="${actionBean.permohonan.catatan ne 'PR'}">
                <label>Tempoh dipohon :</label>
                ${actionBean.permohonan.tempohBulan}
                </c:if>
            </p>
            <p>
                <label>Butir-butir Rayuan :</label>
                <s:textarea rows="5" cols="50" name="butirRayuan" value="${actionBean.permohonan.sebab}" readonly="true"/> 
            </p>
                </c:if>
        </fieldset >
    </div>
</s:form>


